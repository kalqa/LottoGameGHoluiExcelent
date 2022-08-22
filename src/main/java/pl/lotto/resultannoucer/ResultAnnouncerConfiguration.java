package pl.lotto.resultannoucer;

public class ResultAnnouncerConfiguration {
    public ResultAnnouncerFacade buildModuleForClient() {
        return new ResultAnnouncerFacade();
    }

    public ResultAnnouncerFacade buildModuleForTest() {
        return buildModuleForClient();
    }
}
