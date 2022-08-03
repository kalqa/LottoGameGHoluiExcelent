package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TicketDto(String hash,
                        List<Integer> userNumbers,
                        LocalDateTime dateAndTimeNextDraw) {

}
