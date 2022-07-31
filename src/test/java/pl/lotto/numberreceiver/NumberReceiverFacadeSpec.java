package pl.lotto.numberreceiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NumberReceiverFacadeSpec implements SampleTicket {

    @Test
    @DisplayName("should_return_correct_message_when_user_inputted_six_numbers")
    public void should_return_correct_message_when_user_inputted_six_numbers() {
        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests
                (ticketGenerator, ticketRepository);
        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        TicketDto ticketDto = sampleTicketDtoWithTestHash(Arrays.asList(1, 2, 3, 4, 5, 6), LocalDateTime.of(2022, 7, 10, 12, 0));

        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("correct message"), ticketDto);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message when user inputted less than six numbers")
    public void should_return_failed_message_when_user_inputted_less_than_six_numbers() {

        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(ticketGenerator, ticketRepository);
        //when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("you must give exactly six numbers"), null);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    @DisplayName("should return failed message when user inputted more than six numbers")
    public void should_return_failed_message_when_user_inputed_more_than_six_numbers() {

        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(ticketGenerator, ticketRepository);
        //when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("you must give exactly six numbers"), null);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    @DisplayName("should return failed message when user inputted number out of range")
    public void should_return_failed_message_when_user_inputted_number_out_of_range() {
        // given

        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6000);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .buildModuleForTests(ticketGenerator, ticketRepository);
        //when

        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("all numbers should be in range 1-99"), null);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @Test
    @DisplayName("should return failed message when user you must give exactly six numbers " +
            "all numbers should be in range 1-99" +
            "you must give exactly six not repeatable numbers"
    )
    public void should_return_failed_message_when_user_inputted_numbers() {

        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6000, 6000);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .buildModuleForTests(ticketGenerator, ticketRepository);
        //when

        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(
                List.of("you must give exactly six numbers",
                        "all numbers should be in range 1-99",
                        "you must give exactly six not repeatable numbers"), null);
        assertThat(actualResult).isEqualTo(expectedResult);
    }


    @Test
    @DisplayName("should return that ticket has been added when user inputted none repeatedNumbers")
    public void should_return_that_ticket_has_been_added_when_user_inputted_none_repeatedNumbers() {

        // given
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = mock(TicketRepository.class);

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .buildModuleForTests(ticketGenerator, ticketRepository);


        Map<LocalDateTime, Ticket> mapForTest = generateMapForTest(numbersFromUser);

        given(ticketRepository.getAllTickets()).willReturn(mapForTest);

//        when
        List<Ticket> actual = numberReceiverFacade
                .userNumbersForGivenDate(LocalDate.of(1, 1, 1));

        //then
        List<Ticket> expected = new ArrayList<>(Arrays.asList(new Ticket("testHash",
                numbersFromUser
                , LocalDateTime.of(1, 1, 1, 1, 1)
        )));
        assertThat(actual).isEqualTo(expected);
    }

    private Map<LocalDateTime, Ticket> generateMapForTest(List<Integer> numbersFromUser) {
        Map<LocalDateTime, Ticket> mapForTest = new ConcurrentHashMap<>();
        for (int i = 1; i < 10; i++) {
            LocalDate currentLocalDate = LocalDate.of(i, i, i);
            LocalTime currentLocalTime = LocalTime.of(i, i);
            LocalDateTime currentLocalDateTime = LocalDateTime.of(currentLocalDate, currentLocalTime);
            Ticket ticket = Ticket.builder()
                    .hash("testHash")
                    .userNumbers(numbersFromUser)
                    .dateAndTimeNextDraw(currentLocalDateTime)
                    .build();
            mapForTest.put(currentLocalDateTime, ticket);
        }
        return mapForTest;
    }
}