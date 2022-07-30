package pl.lotto.numberreceiver;

public class NumberReceiverConfiguration {

    public NumberReceiverFacade buildModuleForClient(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        NumberValidatorMessageConverter numberValidatorMessageConverter = new NumberValidatorMessageConverter();
        TicketAbcd ticketAbcd = new TicketAbcd(ticketGenerator, ticketRepository);
        return new NumberReceiverFacade(numberValidator, numberValidatorMessageConverter, ticketAbcd);
    }

    public NumberReceiverFacade buildModuleForTests(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        return buildModuleForClient(ticketGenerator, ticketRepository);
    }
}
