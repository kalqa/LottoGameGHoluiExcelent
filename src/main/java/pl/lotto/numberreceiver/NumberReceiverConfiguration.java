package pl.lotto.numberreceiver;

import java.util.LinkedHashSet;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade buildModuleForClient(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        return new NumberReceiverFacade(numberValidator, ticketGenerator);
    }

    public NumberReceiverFacade buildModuleForTests(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        return buildModuleForClient(ticketGenerator, ticketRepository);
    }
}
