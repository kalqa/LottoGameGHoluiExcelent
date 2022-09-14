package pl.lotto.numbergenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Getter
public class WinnerNumbers {
    List<Integer> numbers;
    @Id
    LocalDateTime dateOfWinnerNumbers;
}
