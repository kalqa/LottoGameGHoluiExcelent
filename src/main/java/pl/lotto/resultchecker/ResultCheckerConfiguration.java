package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultCheckerConfiguration {

    @Bean
    public ResultCheckerFacade resultCheckerFacade(WinnerTicketCheckable winnerTicketCheckable,
                                                   WinnerDataLoader winnerDataLoader,
                                                   WinnersTicketDataBase winnersTicketDataBase) {


        return new ResultCheckerFacade(winnerTicketCheckable, winnerDataLoader, winnersTicketDataBase);
    }

    public ResultCheckerFacade resultCheckerFacadeTest(WinnerTicketCheckable winnerTicketCheckable,
                                                       WinnerDataLoader winnerDataLoader,
                                                       WinnersTicketDataBase winnersTicketDataBase) {
        return resultCheckerFacade(winnerTicketCheckable, winnerDataLoader, winnersTicketDataBase);
    }
}