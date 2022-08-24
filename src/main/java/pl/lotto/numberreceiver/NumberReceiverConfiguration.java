package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    public NumberReceiverFacade numberReceiverFacade(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        NumberValidatorMessageConverter numberValidatorMessageConverter = new NumberValidatorMessageConverter();
        TicketStorage ticketStorage = new TicketStorage(ticketGenerator, ticketRepository);
        return new NumberReceiverFacade(numberValidator, numberValidatorMessageConverter, ticketStorage);
    }

    public NumberReceiverFacade numberReceiverFacadeTest(TicketGenerable ticketGenerator, TicketRepository ticketRepository) {
        return numberReceiverFacade(ticketGenerator, ticketRepository);
    }
}
