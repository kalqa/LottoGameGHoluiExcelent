package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ListTicketDtoMapper {

    static List<TicketDto> mapListOfTicketToTicketDto(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> new TicketDto(ticket.getHash(),
                        ticket.getUserNumbers(),
                        ticket.getNextDrawDate()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
