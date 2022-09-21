package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;

@AllArgsConstructor
public class NumberGeneratorFacade {

    WinningNumberGenerable winningNumberGenerator;
    WinningNumberRepository repository;


    public NumberGeneratorResultDto generateNumbersForDate(LocalDateTime currentDate) {
        List<Integer> generated = winningNumberGenerator.generateWinningNumbers();
        WinnerNumbers winnerNumbers = new WinnerNumbers(generated, currentDate);
        repository.save(winnerNumbers);
        return new NumberGeneratorResultDto(generated);

    }

    public NumberGeneratorResultDto winningNumbersForDate(LocalDateTime date) {
        List<Integer> winningNumbers = repository.findByDateOfWinnerNumbers(date).numbers;
        return new NumberGeneratorResultDto(winningNumbers);
    }
}
