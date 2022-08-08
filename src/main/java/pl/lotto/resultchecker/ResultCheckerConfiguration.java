package pl.lotto.resultchecker;

import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;

public class ResultCheckerConfiguration {

    public ResultCheckerFacade buildModuleForClient(NumberGeneratorFacade numberGeneratorFacade,
                                                    NumberReceiverFacade numberReceiverFacade,
                                                    WinnerTicketCheckable winnerTicketCheckable,
                                                    WinnerDataLoader winnerDataLoader) {


        return new ResultCheckerFacade(numberGeneratorFacade, numberReceiverFacade, winnerTicketCheckable, winnerDataLoader);
    }

    public ResultCheckerFacade buildModuleForTest(NumberGeneratorFacade numberGeneratorFacade,
                                                  NumberReceiverFacade numberReceiverFacade,
                                                  WinnerTicketCheckable winnerTicketCheckable,
                                                  WinnerDataLoader winnerDataLoader) {
        return buildModuleForClient(numberGeneratorFacade, numberReceiverFacade, winnerTicketCheckable, winnerDataLoader);
    }
}