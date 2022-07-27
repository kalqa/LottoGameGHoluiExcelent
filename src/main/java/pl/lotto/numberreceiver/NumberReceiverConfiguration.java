package pl.lotto.numberreceiver;

import java.util.LinkedHashSet;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade buildModuleForClient() {
        NumberValidator numberValidator = new NumberValidator();
        TicketRepository ticketRepositoryImpl = new TicketRepositoryImpl(new LinkedHashSet<>());
        return new NumberReceiverFacade(numberValidator, ticketRepositoryImpl);
    }

    public NumberReceiverFacade buildModuleForTests() {
        return buildModuleForClient();
    }
}
