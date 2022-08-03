package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;
import static org.assertj.core.api.Assertions.assertThat;

class NumberGeneratorFacadeSpec {

    @Test
    public void should_return_winning_numbers() {
        // given
        NumberGeneratorConfiguration numberGeneratorConfiguration = new NumberGeneratorConfiguration();
        NumberGeneratorFacade numberReceiverFacade = numberGeneratorConfiguration.buildModuleForTests();
        numberReceiverFacade.generateNumbers();

        // when
        NumberGeneratorResultDto winningNumbers = numberReceiverFacade.winningNumbersForDate(LocalDateTime.now());

        // then
        List<Integer> expectedWinningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        NumberGeneratorResultDto expectedDto = new NumberGeneratorResultDto(expectedWinningNumbers);
        assertThat(winningNumbers).isEqualTo(expectedDto);

    }

}
