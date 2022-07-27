package pl.lotto.numberreceiver.dto;

import pl.lotto.numberreceiver.Ticket;

public record NumberReceiverResultDto(String message, Ticket ticket) {

    public NumberReceiverResultDto(String message) {
        this(message, null);
    }

    public NumberReceiverResultDto(String message, Ticket ticket) {
        this.message = message;
        this.ticket = ticket;
    }

}
