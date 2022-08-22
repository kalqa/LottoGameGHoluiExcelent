package pl.lotto.resultchecker;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultCheckerDto;

@AllArgsConstructor
public class ResultCheckerFacade {

    NumberGeneratorFacade numberGeneratorFacade;
    NumberReceiverFacade numberReceiverFacade;
    WinnerTicketCheckable winnerTicketCheckable;
    WinnerDataLoader winnerDataLoader;

    public ResultCheckerDto winners(LocalDateTime dateToGetWinnersTicket) {
        List<TicketDto> winnersTicket = getWinnersTicket(dateToGetWinnersTicket);
        return new ResultCheckerDto(winnersTicket);
    }

    private List<TicketDto> getWinnersTicket(LocalDateTime dateToGetWinnersTicket) {
        List<Integer> winnerNumbers = winnerDataLoader.getWinnerNumbers(dateToGetWinnersTicket);
        List<TicketDto> tickets = winnerDataLoader.getTickets(dateToGetWinnersTicket);
        List<TicketDto> ticketDtos = winnerTicketCheckable.checkWhichTicketWon(tickets, winnerNumbers);
        return ticketDtos;
    }

}
