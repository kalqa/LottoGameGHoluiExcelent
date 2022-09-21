package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

//@AllArgsConstructor
@Component
class WinnerDataLoader {

    NumberGeneratorFacade numberGeneratorFacade;

    NumberReceiverFacade numberReceiverFacade;

    @Autowired
    public WinnerDataLoader(NumberGeneratorFacade numberGeneratorFacade, NumberReceiverFacade numberReceiverFacade) {
        this.numberGeneratorFacade = numberGeneratorFacade;
        this.numberReceiverFacade = numberReceiverFacade;
    }

    List<Integer> getWinnerNumbers(LocalDateTime dateToGetWinnersTicket) {
        List<Integer> integers = numberGeneratorFacade.winningNumbersForDate(dateToGetWinnersTicket).winningNumbers();
        return integers;
    }

    List<TicketDto> getTickets(LocalDateTime dateToGetWinnersTicket) {
        return numberReceiverFacade.userNumbersForGivenDate(dateToGetWinnersTicket);
    }
}
