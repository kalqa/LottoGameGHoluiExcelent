package pl.lotto.resultchecker.dto;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

public record ResultCheckerDto(List<TicketDto> winnersTickets) {

}
