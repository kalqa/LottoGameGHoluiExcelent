package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

public interface WinnersTicketDataBase {

    void addWinnerTicketsToDataBase(List<TicketDto> ticketDto);

    boolean checkIfUserWon(String id);
}
