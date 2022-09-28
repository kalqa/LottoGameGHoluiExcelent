package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;

@AllArgsConstructor
public class NumberGeneratorFacade {

    WinningNumberGenerable winningNumberGenerator;
    WinningNumberRepository repository;


    public NumberGeneratorResultDto generateNumbersForDate(LocalDateTime currentDate) {
        List<Integer> generated = winningNumberGenerator.generateWinningNumbers();
        WinnerNumbers winnerNumbers = new WinnerNumbers(generated, currentDate);
        boolean exists = repository.existsByDrawDate(currentDate);
        if(exists){
            throw new DuplicateKeyException("");
        }
        repository.save(winnerNumbers);
        return new NumberGeneratorResultDto(generated);
    }

    public NumberGeneratorResultDto winningNumbersForDate(LocalDateTime date) {
        Optional<WinnerNumbers> firstByDrawDate = repository.findFirstByDrawDate(date);
        firstByDrawDate.ifPresent(WinnerNumbers::getNumbers);
        return new NumberGeneratorResultDto(winningNumbers);
    }
}
