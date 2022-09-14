package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

class WinnersTicketDataBaseTestImpl implements WinnersTicketDataBase {

    List<TicketDto> winnerTickets;

    public WinnersTicketDataBaseTestImpl(List<TicketDto> winnerTickets) {
        this.winnerTickets = winnerTickets;
    }

    @Override
    public List<TicketDto> save(List<TicketDto> ticketDtos) {
        winnerTickets.addAll(ticketDtos);
        return ticketDtos;
    }

    @Override
    public boolean checkIfUserWon(String id) {
        return winnerTickets.stream()
                .map(TicketDto::hash)
                .anyMatch(hash -> hash.equals(id));
    }



}
