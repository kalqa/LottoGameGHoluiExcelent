package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

class WinnersTicketDataBaseTestImpl implements WinnersTicketDataBase {

    List<TicketDto> winnerTickets;

    public WinnersTicketDataBaseTestImpl(List<TicketDto> winnerTickets) {
        this.winnerTickets = winnerTickets;
    }

    @Override
    public void addWinnerTicketsToDataBase(List<TicketDto> ticketDto) {
        winnerTickets.addAll(ticketDto);
    }

    @Override
    public boolean checkIfUserWon(String id) {
        return winnerTickets.stream()
                .map(TicketDto::hash)
                .anyMatch(hash -> hash.equals(id));
    }
}
