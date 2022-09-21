package pl.lotto.numbergenerator;

import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class NumberGeneratorFacadeSpec {

    @Test
    public void should_return_winning_numbers() {
        // given
        WinningNumberGenerable winningNumberGenerable = new WinningNumberGenerableTestImpl();
        WinningNumberRepository winningNumberRepository = new WinningNumberRepositoryTestImpl(
                new ConcurrentHashMap<>());

        NumberGeneratorConfiguration numberGeneratorConfiguration = new NumberGeneratorConfiguration();
        NumberGeneratorFacade numberReceiverFacade = numberGeneratorConfiguration
                .numberGeneratorFacadeTest(winningNumberGenerable, winningNumberRepository);

        LocalDateTime dateToSave = LocalDateTime.of(1, 1, 1, 1, 1);
        numberReceiverFacade.generateNumbersForDate(dateToSave);

        // when
        NumberGeneratorResultDto winningNumbers = numberReceiverFacade.winningNumbersForDate(dateToSave);

        // then
        List<Integer> expectedWinningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        NumberGeneratorResultDto expectedDto = new NumberGeneratorResultDto(expectedWinningNumbers);
        assertThat(winningNumbers).isEqualTo(expectedDto);
    }

}