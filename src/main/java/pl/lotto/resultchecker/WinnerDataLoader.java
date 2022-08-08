package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
class WinnerDataLoader {

    NumberGeneratorFacade numberGeneratorFacade;
    NumberReceiverFacade numberReceiverFacade;

    List<Integer> getWinnerNumbers(LocalDateTime dateToGetWinnersTicket) {
        return numberGeneratorFacade.winningNumbersForDate(dateToGetWinnersTicket).winningNumbers();
    }

    List<TicketDto> getTickets(LocalDateTime dateToGetWinnersTicket) {
        return numberReceiverFacade.userNumbersForGivenDate(dateToGetWinnersTicket);
    }
}
