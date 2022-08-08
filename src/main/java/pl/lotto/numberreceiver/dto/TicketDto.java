package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TicketDto(String hash,
                        List<Integer> userNumbers,
                        LocalDateTime dateAndTimeNextDraw) {
    @Override
    public String hash() {
        return hash;
    }

    @Override
    public List<Integer> userNumbers() {
        return userNumbers;
    }

    @Override
    public LocalDateTime dateAndTimeNextDraw() {
        return dateAndTimeNextDraw;
    }
}