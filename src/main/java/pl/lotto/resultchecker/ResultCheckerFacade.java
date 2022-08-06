package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
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

    public ResultCheckerDto winners() {
        return new ResultCheckerDto(null);
    }

    public List<TicketDto> getWinnersTicket(LocalDateTime dateToGetWinnersTicket) {
        List<Integer> winnerNumbers = getWinnerNumbers(dateToGetWinnersTicket);
//        List<Ticket> tickets = numberReceiverFacade.userNumbersForGivenDate(dateToGetWinnersTicket);


        return winnerTicketCheckable.checkWhichTicketWon();
    }

    private List<Integer> getWinnerNumbers(LocalDateTime dateToGetWinnersTicket) {
        return numberGeneratorFacade.winningNumbersForDate(dateToGetWinnersTicket).winningNumbers();
    }
}