package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultCheckerDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ResultCheckerFacadeSpec {

    @Test
    void shouldReturnThatTwoTicketsWon() {
        //given 
        NumberGeneratorFacade numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        NumberGeneratorResultDto numberGeneratorResultDtoForMock = new NumberGeneratorResultDto(winningNumbers);

        LocalDateTime dayOfResult = LocalDateTime.of(1, 1, 1, 1, 1);
        given(numberGeneratorFacade.winningNumbersForDate(dayOfResult)).willReturn(numberGeneratorResultDtoForMock);

        List<TicketDto> allTicketForDateDayOfResult = Arrays.asList(
                new TicketDto("hash1", Arrays.asList(1, 2, 3, 4, 5, 6), dayOfResult),
                new TicketDto("hash2", Arrays.asList(2, 3, 4, 5, 6, 9), dayOfResult)
        );

        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        given(numberReceiverFacade.userNumbersForGivenDate(dayOfResult)).willReturn(allTicketForDateDayOfResult);


        WinnerTicketCheckable winnerTicketCheckable = new WinnerTicketCheckableImpl();

        WinnerDataLoader winnerDataLoader = new WinnerDataLoader(numberGeneratorFacade, numberReceiverFacade);

        WinnersTicketDataBase winnersTicketDataBase = new WinnersTicketDataBaseTestImpl(new ArrayList<>());

        ResultCheckerConfiguration resultCheckerConfiguration = new ResultCheckerConfiguration();
        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration
                .resultCheckerFacadeTest(winnerTicketCheckable, winnerDataLoader, winnersTicketDataBase);


        //when
        ResultCheckerDto actualWinnerResultCheckerDto =
                resultCheckerFacade.winners(LocalDateTime.of(1, 1, 1, 1, 1));

        //then
        List<TicketDto> expectedList = Arrays.asList(
                new TicketDto("hash1", Arrays.asList(1, 2, 3, 4, 5, 6), dayOfResult),
                new TicketDto("hash2", Arrays.asList(2, 3, 4, 5, 6, 9), dayOfResult)
        );
        ResultCheckerDto resultCheckerDto = new ResultCheckerDto(expectedList);

        assertThat(actualWinnerResultCheckerDto).isEqualTo(resultCheckerDto);
    }

    @Test
    void shouldReturnThatOneTicketWon() {
        //given
        NumberGeneratorFacade numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        LocalDateTime dayOfResult = LocalDateTime.of(1, 1, 1, 1, 1);

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        NumberGeneratorResultDto numberGeneratorResultDtoForMock = new NumberGeneratorResultDto(winningNumbers);


        given(numberGeneratorFacade.winningNumbersForDate(dayOfResult)).willReturn(numberGeneratorResultDtoForMock);


        List<TicketDto> allTicketForDateDayOfResult = Arrays.asList
                (new TicketDto("hash1", Arrays.asList(1, 2, 35, 45, 55, 65), dayOfResult),
                        new TicketDto("hash2", Arrays.asList(2, 3, 4, 5, 6, 9), dayOfResult)
                );

        given(numberReceiverFacade.userNumbersForGivenDate(dayOfResult)).willReturn(allTicketForDateDayOfResult);


        WinnerTicketCheckable winnerTicketCheckable = new WinnerTicketCheckableImpl();

        WinnerDataLoader winnerDataLoader = new WinnerDataLoader(numberGeneratorFacade, numberReceiverFacade);

        WinnersTicketDataBase winnersTicketDataBase = new WinnersTicketDataBaseTestImpl(new ArrayList<>());

        ResultCheckerConfiguration resultCheckerConfiguration = new ResultCheckerConfiguration();
        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration
                .resultCheckerFacadeTest(winnerTicketCheckable, winnerDataLoader, winnersTicketDataBase);

        //when
        ResultCheckerDto actualWinnerResultCheckerDto =
                resultCheckerFacade.winners(LocalDateTime.of(1, 1, 1, 1, 1));

        //then
        List<TicketDto> expectedList = List.of(new TicketDto("hash2", Arrays.asList(2, 3, 4, 5, 6, 9), dayOfResult)
        );
        ResultCheckerDto resultCheckerDto = new ResultCheckerDto(expectedList);

        assertThat(actualWinnerResultCheckerDto).isEqualTo(resultCheckerDto);
    }

    @Test
    void shouldReturnThatNoneTicketWon() {
        //given
        NumberGeneratorFacade numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        LocalDateTime dayOfResult = LocalDateTime.of(1, 1, 1, 1, 1);

        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        NumberGeneratorResultDto numberGeneratorResultDtoForMock = new NumberGeneratorResultDto(winningNumbers);


        given(numberGeneratorFacade.winningNumbersForDate(dayOfResult)).willReturn(numberGeneratorResultDtoForMock);


        List<TicketDto> allTicketForDateDayOfResult = new ArrayList<>();

        given(numberReceiverFacade.userNumbersForGivenDate(dayOfResult)).willReturn(allTicketForDateDayOfResult);


        WinnerTicketCheckable winnerTicketCheckable = new WinnerTicketCheckableImpl();

        WinnerDataLoader winnerDataLoader = new WinnerDataLoader(numberGeneratorFacade, numberReceiverFacade);

        WinnersTicketDataBase winnersTicketDataBase = new WinnersTicketDataBaseTestImpl(new ArrayList<>());

        ResultCheckerConfiguration resultCheckerConfiguration = new ResultCheckerConfiguration();
        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration
                .resultCheckerFacadeTest(winnerTicketCheckable, winnerDataLoader, winnersTicketDataBase);

        //when
        ResultCheckerDto actualWinnerResultCheckerDto =
                resultCheckerFacade.winners(LocalDateTime.of(1, 1, 1, 1, 1));

        //then
        List<TicketDto> expectedList = new ArrayList<>();
        ResultCheckerDto resultCheckerDto = new ResultCheckerDto(expectedList);

        assertThat(actualWinnerResultCheckerDto).isEqualTo(resultCheckerDto);
    }
}
