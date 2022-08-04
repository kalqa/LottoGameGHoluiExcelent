package pl.lotto.numberreceiver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeSpec implements SampleTicket {


    public static Stream<Arguments> createArrayWhereTestsPassed() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Arguments.of(Arrays.asList(10, 20, 30, 40, 50, 60)),
                Arguments.of(Arrays.asList(15, 25, 35, 45, 55, 65)),
                Arguments.of(Arrays.asList(17, 27, 37, 47, 57, 67)),
                Arguments.of(Arrays.asList(18, 28, 38, 48, 58, 68))
        );
    }

    public static Stream<Arguments> createArrayWhereAreLessNumbersThanShouldBe() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5)),
                Arguments.of(Arrays.asList(10, 20, 30, 40, 50)),
                Arguments.of(Arrays.asList(15, 25, 35, 45, 55)),
                Arguments.of(Arrays.asList(17, 27, 37, 47, 57)),
                Arguments.of(Arrays.asList(18, 28, 38, 48, 58))
        );
    }

    public static Stream<Arguments> createArrayWhereAreToManyNumbersThanShouldBe() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 70)),
                Arguments.of(Arrays.asList(10, 20, 30, 40, 50, 60, 70)),
                Arguments.of(Arrays.asList(15, 25, 35, 45, 55, 65, 70)),
                Arguments.of(Arrays.asList(17, 27, 37, 47, 57, 67, 70)),
                Arguments.of(Arrays.asList(18, 28, 38, 48, 58, 68, 70))
        );
    }

    public static Stream<Arguments> createArrayWhereNumbersAreNotInRange() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6000)),
                Arguments.of(Arrays.asList(10, 20, 30, 40, 50, 6000)),
                Arguments.of(Arrays.asList(15, 25, 35, 45, 55, 6500)),
                Arguments.of(Arrays.asList(17, 27, 37, 47, 57, 6700)),
                Arguments.of(Arrays.asList(18, 28, 38, 48, 58, 6800))
        );
    }

    public static Stream<Arguments> createArrayWithAllException() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6000, 6000)),
                Arguments.of(Arrays.asList(10, 20, 30, 40, 50, 6000, 6000)),
                Arguments.of(Arrays.asList(15, 25, 35, 45, 55, 6500, 6500)),
                Arguments.of(Arrays.asList(17, 27, 37, 47, 57, 6700, 6700)),
                Arguments.of(Arrays.asList(18, 28, 38, 48, 58, 6800, 6800))
        );
    }

    // wersja spy

    @ParameterizedTest
    @DisplayName("should_return_correct_message_when_user_inputted_six_numbers")
    @MethodSource("createArrayWhereTestsPassed")
    public void createArrayWhereTestsPassed(List<Integer> numbersFromUser) {
        // given
//        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests
                (ticketGenerator, ticketRepository);
        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        TicketDto ticketDto = sampleTicketDtoWithTestHash(numbersFromUser, LocalDateTime
                .of(2022, 7, 10, 12, 0));

        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("correct message"), ticketDto);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @DisplayName("should return failed message when user inputted less than six numbers")
    @MethodSource("createArrayWhereAreLessNumbersThanShouldBe")
    public void should_return_failed_message_when_user_inputted_less_than_six_numbers(List<Integer> numbersFromUser) {
        // given
//        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(ticketGenerator, ticketRepository);
        //when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("you must give exactly six numbers"), null);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @ParameterizedTest
    @DisplayName("should return failed message when user inputted more than six numbers")
    @MethodSource("createArrayWhereAreToManyNumbersThanShouldBe")
    public void should_return_failed_message_when_user_inputed_more_than_six_numbers(List<Integer> numbersFromUser) {
        // given
//        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        TicketGenerable ticketGenerator = new TicketGeneratorTestImpl(numbersFromUser);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(new HashMap<>());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests(ticketGenerator, ticketRepository);
        //when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto(List.of("you must give exactly six numbers"), null);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @ParameterizedTest
    @DisplayName("should return failed message when user inputted number out of range")
    @MethodSource("createArrayWhereNumbersAreNotInRange")
    public void should_return_failed_message_when_user_inputted_number_out_of_range(List<Integer> numbersFromUser) {
        // given
//        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6000);
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

    @ParameterizedTest
    @DisplayName("should return failed message when user you must give exactly six numbers " +
            "all numbers should be in range 1-99" +
            "you must give exactly six not repeatable numbers"
    )
    @MethodSource("createArrayWithAllException")
    public void should_return_failed_message_when_user_inputted_numbers(List<Integer> numbersFromUser) {
        // given
//        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6000, 6000);
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
//        TicketRepository ticketRepository = mock(TicketRepositoryTestImpl.class);
        TicketRepository ticketRepository = new TicketRepositoryTestImpl(generateMapForTest(numbersFromUser));

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration()
                .buildModuleForTests(ticketGenerator, ticketRepository);


//        Map<String, Ticket> mapForTest = generateMapForTest(numbersFromUser);

//        given(ticketRepository.getAllTickets()).willReturn(mapForTest);

//        when
        List<Ticket> actual = numberReceiverFacade
                .userNumbersForGivenDate(LocalDateTime.of(1, 1, 1, 1, 1));

        //then
        Ticket ticket = new Ticket("testHash1",
                numbersFromUser, LocalDateTime.of(1, 1, 1, 1, 1)
        );
        List<Ticket> expected = List.of(ticket);
        assertThat(actual).isEqualTo(expected);
    }

    private Map<String, Ticket> generateMapForTest(List<Integer> numbersFromUser) {
        Map<String, Ticket> mapForTest = new ConcurrentHashMap<>();
        for (int i = 1; i < 10; i++) {
            LocalDate currentLocalDate = LocalDate.of(i, i, i);
            LocalTime currentLocalTime = LocalTime.of(i, i);
            LocalDateTime currentLocalDateTime = LocalDateTime.of(currentLocalDate, currentLocalTime);
            String testHash = "testHash" + i;
            Ticket ticket = Ticket.builder()
                    .hash(testHash)
                    .userNumbers(numbersFromUser)
                    .dateAndTimeNextDraw(currentLocalDateTime)
                    .build();
            mapForTest.put(testHash, ticket);
        }
        return mapForTest;
    }
}
