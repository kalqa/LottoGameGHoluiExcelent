package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultCheckerDto;

import java.time.LocalDateTime;
import java.util.List;

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
        return winnerTicketCheckable.checkWhichTicketWon(tickets, winnerNumbers);
    }

}