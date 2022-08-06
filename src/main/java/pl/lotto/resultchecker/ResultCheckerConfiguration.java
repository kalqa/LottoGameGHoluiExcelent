package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;

public class ResultCheckerConfiguration {

    public ResultCheckerFacade buildModuleForClient(NumberGeneratorFacade numberGeneratorFacade,
                                                    NumberReceiverFacade numberReceiverFacade,
                                                    WinnerTicketCheckable winnerTicketCheckable) {
        return new ResultCheckerFacade(numberGeneratorFacade, numberReceiverFacade, winnerTicketCheckable);
    }

    public ResultCheckerFacade buildModuleForTest(NumberGeneratorFacade numberGeneratorFacade,
                                                  NumberReceiverFacade numberReceiverFacade,
                                                  WinnerTicketCheckable winnerTicketCheckable) {
        return buildModuleForClient(numberGeneratorFacade, numberReceiverFacade, winnerTicketCheckable);
    }
}