package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class WinnerTicketCheckableImpl implements WinnerTicketCheckable {
    @Override
    public List<TicketDto> checkWhichTicketWon(List<TicketDto> tickets, List<Integer> winnerNumbers) {
        return tickets.stream()
                .filter(ticketDto -> areTicketsNumbersContainsMoreThanTwoTheSameNumbers(ticketDto, winnerNumbers))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    boolean areTicketsNumbersContainsMoreThanTwoTheSameNumbers(TicketDto ticket, List<Integer> winnerNumbers) {
        return ticket.userNumbers()
                .stream()
                .filter(winnerNumbers::contains)
                .count() >= 3;
    }

}