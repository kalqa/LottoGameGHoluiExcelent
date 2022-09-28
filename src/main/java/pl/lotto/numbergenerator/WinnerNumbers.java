package pl.lotto.numbergenerator;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document
public class WinnerNumbers {
    @Id
    UUID id;

    List<Integer> numbers;

    @Indexed(unique = true)
    LocalDateTime drawDate;

    WinnerNumbers(List<Integer> numbers, LocalDateTime dateOfWinnerNumbers) {
        this.id = UUID.randomUUID();
        this.numbers = numbers;
        this.drawDate = dateOfWinnerNumbers;
    }
}
