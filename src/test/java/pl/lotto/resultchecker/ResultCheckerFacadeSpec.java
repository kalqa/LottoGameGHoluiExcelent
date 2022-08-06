package pl.lotto.resultchecker;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;

class ResultCheckerFacadeSpec {

    @Test
    void shouldReturnWhichTicketsHaveWon() {
        //given 
        NumberGeneratorFacade numberGeneratorFacade = mock(NumberGeneratorFacade.class);
        NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
        WinnerTicketCheckable winnerTicketCheckable = new WinnerTicketCheckableTestImpl();
        ResultCheckerConfiguration resultCheckerConfiguration = new ResultCheckerConfiguration();
        ResultCheckerFacade resultCheckerFacade = resultCheckerConfiguration
                .buildModuleForTest(numberGeneratorFacade, numberReceiverFacade, winnerTicketCheckable);

        //when
        resultCheckerFacade.getWinnersTicket(LocalDateTime.of(1, 1, 1, 1, 1, 1));


//        resultCheckerFacade.getWinnersTicket();

        //then


    }

}
