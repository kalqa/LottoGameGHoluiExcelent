package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

public class NumberReceiverResultMapper {

    static NumberReceiverResultDto mapToDto(String message, Ticket ticket) {
        return new NumberReceiverResultDto(
                message,
                new TicketDto(ticket.getHash(), ticket.userNumbers, ticket.dateAndTimeNextDraw)
        );
    }

}
