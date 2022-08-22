package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    public NumberReceiverFacade buildModuleForClient(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        NumberValidatorMessageConverter numberValidatorMessageConverter = new NumberValidatorMessageConverter();
        TicketStorage ticketStorage = new TicketStorage(ticketGenerator, ticketRepository);
        return new NumberReceiverFacade(numberValidator, numberValidatorMessageConverter, ticketStorage);
    }

    public NumberReceiverFacade buildModuleForTests(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        return buildModuleForClient(ticketGenerator, ticketRepository);
    }
}
