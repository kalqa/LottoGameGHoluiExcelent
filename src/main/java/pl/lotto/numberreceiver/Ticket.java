package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
public class Ticket {
    String hash;
    List<Integer> userNumbers;
    LocalDateTime dateAndTimeNextDraw;
}
