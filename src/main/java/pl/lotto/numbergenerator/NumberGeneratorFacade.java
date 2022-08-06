package pl.lotto.numbergenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;

@AllArgsConstructor
public class NumberGeneratorFacade {

    WinningNumberGenerable winningNumberGenerator;
    WinningNumberRepository repository;

    public void generateNumbersForDate(LocalDateTime currentDate) {
        List<Integer> generated = winningNumberGenerator.generateWinningNumbers();
        repository.save(generated, currentDate);
    }

    public NumberGeneratorResultDto winningNumbersForDate(LocalDateTime date) {
        List<Integer> winningNumbers = repository.getWinningNumbersForGivenDate(date);
        return new NumberGeneratorResultDto(winningNumbers);
    }
}
