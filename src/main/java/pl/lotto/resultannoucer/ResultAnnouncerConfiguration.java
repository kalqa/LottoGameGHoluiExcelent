package pl.lotto.resultannoucer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Configuration
public class ResultAnnouncerConfiguration {
    @Bean
    public ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        return new ResultAnnouncerFacade(resultCheckerFacade);
    }

    public ResultAnnouncerFacade resultAnnouncerFacadeTest(ResultCheckerFacade resultCheckerFacade) {
        return resultAnnouncerFacade(resultCheckerFacade);
    }
}
