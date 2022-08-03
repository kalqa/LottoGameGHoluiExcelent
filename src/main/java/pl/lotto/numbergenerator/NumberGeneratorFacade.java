package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;

public class NumberGeneratorFacade {

    WinningNumberGenerable winningNumberGenerator;
    WinningNumberRepository repository;

    public void generateNumbers() {
        List<Integer> generated = winningNumberGenerator.generate();
        repository.save(generated);
    }

    public NumberGeneratorResultDto winningNumbersForDate(LocalDateTime date) {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        return new NumberGeneratorResultDto(winningNumbers);
    }
}
