package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class WinnerTickets {
    String hash;
    List<Integer> userNumbers;
    LocalDateTime dateAndTimeNextDraw;
}
