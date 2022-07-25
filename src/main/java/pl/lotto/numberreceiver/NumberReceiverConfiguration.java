package pl.lotto.numberreceiver;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade buildModuleForClient() {
        return new NumberReceiverFacade();
    }

    public NumberReceiverFacade buildModuleForTests() {
        return new NumberReceiverFacade();
    }
}
