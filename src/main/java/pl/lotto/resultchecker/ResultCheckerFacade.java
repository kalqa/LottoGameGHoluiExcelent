package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultCheckerDto;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class ResultCheckerFacade {

    WinnerTicketCheckable winnerTicketCheckable;
    WinnerDataLoader winnerDataLoader;
    WinnersTicketDataBase winnersTicketDataBase;

    public WinnersTicketDataBase getWinnersTicketDataBase() {
        return winnersTicketDataBase;
    }

    public ResultCheckerDto winners(LocalDateTime dateToGetWinnersTicket) {
        // this method return all winning TicketsDTO
        List<TicketDto> winnersTicket = getWinnersTicket(dateToGetWinnersTicket);
        return new ResultCheckerDto(winnersTicket);
    }

    public boolean winner(String userId) {
        WinnersTicketDataBase winnersTicketDataBase = getWinnersTicketDataBase();
        return winnersTicketDataBase.checkIfUserWon(userId);
    }

    private List<TicketDto> getWinnersTicket(LocalDateTime dateToGetWinnersTicket) {
        List<Integer> winnerNumbers = winnerDataLoader.getWinnerNumbers(dateToGetWinnersTicket);
        List<TicketDto> tickets = winnerDataLoader.getTickets(dateToGetWinnersTicket);
        List<TicketDto> ticketDtos = winnerTicketCheckable.checkWhichTicketWon(tickets, winnerNumbers);
        winnersTicketDataBase.addWinnerTicketsToDataBase(ticketDtos);
        return ticketDtos;
    }

}
