package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeSpec implements SampleTicket {

    @Test
    @DisplayName("should_return_correct_message_when_user_inputted_six_numbers")
    public void should_return_correct_message_when_user_inputted_six_numbers() {
        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(ticketGenerator, new TicketRepositoryTestImpl(new HashSet<>()));
        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        TicketDto ticketDto = sampleTicketWithTestHash(Arrays.asList(1, 2, 3, 4, 5, 6)
                , LocalDateTime.of(2022, 7, 10, 12, 0));

        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("correct message"), ticketDto);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message when user inputed less than six numbers")
    public void should_return_failed_message_when_user_inputed_less_than_six_numbers() {

        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashSet<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .buildModuleForTests(ticketGenerator, ticketRepository);
        //when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("you must give exactly six numbers"),
                null);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    @DisplayName("should return failed message when user inputed more than six numbers")
    public void should_return_failed_message_when_user_inputed_more_than_six_numbers() {

        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashSet<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .buildModuleForTests(ticketGenerator, ticketRepository);
        //when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("you must give exactly six numbers"),
                null);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    @DisplayName("should return failed message when user inputed number out of range")
    public void should_return_failed_message_when_user_inputed_number_out_of_range() {
        // given

        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6000);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashSet<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .buildModuleForTests(ticketGenerator, ticketRepository);
        //when

        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("all numbers should be in range 1-99"),
                null);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

//
//    @Test
//    @DisplayName("should find the ticket by hash in TicketRepository")
//    public void should_find_the_ticket_by_hash() {
//        // given
//        String hash = UUID.randomUUID().toString();
//        Ticket ticket = new Ticket(hash, List.of(1, 2, 3, 4, 5, 6), LocalDateTime.now());
//        // when
//        TicketRepositoryImpl ticketRepositoryImpl = new TicketRepositoryImpl(new LinkedHashSet<>());
//        ticketRepositoryImpl.saveTicket(ticket);
//        // then
//        assertThat(ticketRepositoryImpl.findTicketByHash(hash).get()).isEqualTo(ticket);
//    }
//
//
//    @Test
//    @DisplayName("should return that ticket has been added when user inputted none repeatedNumbers")
//    public void should_return_that_ticket_has_been_added_when_user_inputted_none_repeatedNumbers() {
//        // given
//        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(ticketGenerator);
//        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
//        // when
//        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
//        // then
//
//        LocalDate theNextSaturday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
//        LocalDateTime theNextSaturdayDraw = LocalDateTime.of(theNextSaturday, LocalTime.of(12, 0));
//        Ticket ticket = Ticket.builder()
//                .hash(actualResult.ticket().getHash())
//                .userNumbers(numbersFromUser)
//                .dateAndTimeNextDraw(theNextSaturdayDraw)
//                .build();
//        assertThat(actualResult.ticket()).isEqualTo(ticket);
//    }


}
