package pl.lotto.resultannoucer;

import pl.lotto.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerConfiguration {
    public ResultAnnouncerFacade buildModuleForClient(ResultCheckerFacade resultCheckerFacade) {
        return new ResultAnnouncerFacade(resultCheckerFacade);
    }

    public ResultAnnouncerFacade buildModuleForTest(ResultCheckerFacade resultCheckerFacade) {
        return buildModuleForClient(resultCheckerFacade);
    }
}
