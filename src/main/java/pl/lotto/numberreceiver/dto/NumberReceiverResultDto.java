package pl.lotto.numberreceiver.dto;

import java.util.List;

public record NumberReceiverResultDto(List<String> message, TicketDto ticket) {
    @Override
    public List<String> message() {
        return message;
    }

    @Override
    public TicketDto ticket() {
        return ticket;
    }
}