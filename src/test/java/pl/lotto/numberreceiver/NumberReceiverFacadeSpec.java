package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeSpec {

    @Test
    public void should_return_correct_message_when_user_inputted_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("correct message");
        assertThat(actualResult.message()).isEqualTo(expectedResult.message());
    }

    @Test
    @DisplayName("should return failed message when user inputed less than six numbers")
    public void should_return_failed_message_when_user_inputed_less_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("you must give exactly six numbers");
        assertThat(actualResult.message()).isEqualTo(expectedResult.message());
    }

    @Test
    @DisplayName("should return failed message when user inputed more than six numbers")
    public void should_return_failed_message_when_user_inputed_more_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("you must give exactly six numbers");
        assertThat(actualResult.message()).isEqualTo(expectedResult.message());
    }

    @Test
    @DisplayName("should return failed message when user inputed number out of range")
    public void should_return_failed_message_when_user_inputed_number_out_of_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 300, 4, 5, 6);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("all numbers should be in range 1-99");
        assertThat(actualResult.message()).isEqualTo(expectedResult.message());
    }

    @Test
    @DisplayName("should return failed message when user inputed repeated number out of range")
    public void should_return_failed_message_when_user_inputed_repeated_number_out_of_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 50, 50, 6);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("you must give exactly six not repeatable numbers");
        assertThat(actualResult.message()).isEqualTo(expectedResult.message());
    }

    @Test
    @DisplayName("should find the ticket by hash in TicketRepository")
    public void should_find_the_ticket_by_hash() {
        // given
        String hash = UUID.randomUUID().toString();
        Ticket ticket = new Ticket(hash, List.of(1, 2, 3, 4, 5, 6), LocalDateTime.now());
        // when
        TicketRepositoryImpl ticketRepositoryImpl = new TicketRepositoryImpl(new LinkedHashSet<>());
        ticketRepositoryImpl.saveTicket(ticket);
        // then
        assertThat(ticketRepositoryImpl.findTicketByHash(hash).get()).isEqualTo(ticket);
    }


    @Test
    @DisplayName("should return that ticket has been added when user inputted none repeatedNumbers")
    public void should_return_that_ticket_has_been_added_when_user_inputted_none_repeatedNumbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then

        LocalDate theNextSaturday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        LocalDateTime theNextSaturdayDraw = LocalDateTime.of(theNextSaturday, LocalTime.of(12, 0));
        Ticket ticket = Ticket.builder()
                .hash(actualResult.ticket().getHash())
                .userNumbers(numbersFromUser)
                .dateAndTimeNextDraw(theNextSaturdayDraw)
                .build();
        assertThat(actualResult.ticket()).isEqualTo(ticket);
    }


}
