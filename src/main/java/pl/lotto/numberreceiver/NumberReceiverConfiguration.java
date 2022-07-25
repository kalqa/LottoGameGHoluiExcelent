package pl.lotto.numberreceiver;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade buildModuleForClient() {
        NumberValidator numberValidator = new NumberValidator();
        return new NumberReceiverFacade(numberValidator);
    }

    public NumberReceiverFacade buildModuleForTests() {
        return buildModuleForClient();
    }
}
