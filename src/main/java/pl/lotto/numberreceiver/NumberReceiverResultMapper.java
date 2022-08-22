package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

class NumberReceiverResultMapper {

    static NumberReceiverResultDto mapToDto(List<String> message, Ticket ticket) {
        return new NumberReceiverResultDto(
                message,
                new TicketDto(ticket.getHash(), ticket.userNumbers, ticket.nextDrawDate)
        );
    }

}
