package pl.lotto.resultchecker;

public class ResultCheckerConfiguration {

    public ResultCheckerFacade buildModuleForClient(WinnerTicketCheckable winnerTicketCheckable,
                                                    WinnerDataLoader winnerDataLoader,
                                                    WinnersTicketDataBase winnersTicketDataBase) {


        return new ResultCheckerFacade(winnerTicketCheckable, winnerDataLoader, winnersTicketDataBase);
    }

    public ResultCheckerFacade buildModuleForTest(WinnerTicketCheckable winnerTicketCheckable,
                                                  WinnerDataLoader winnerDataLoader,
                                                  WinnersTicketDataBase winnersTicketDataBase) {
        return buildModuleForClient(winnerTicketCheckable, winnerDataLoader, winnersTicketDataBase);
    }
}