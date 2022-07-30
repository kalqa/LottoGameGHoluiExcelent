package pl.lotto.numberreceiver.dto;

import java.util.List;

public record NumberReceiverResultDto(List<String> message, TicketDto ticket) {

}
