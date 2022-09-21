package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.numberreceiver.dto.TicketDto;

@AllArgsConstructor
//@Component
public class NumberReceiverFacade {

    NumberValidator numberValidator;
    NumberValidatorMessageConverter numberValidatorMessageConverter;
    TicketStorage ticketStorage;

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        List<NumberValidatorMessage> enumValidatorMessage = numberValidator.validate(numbersFromUser);
        List<String> validatorMessage = numberValidatorMessageConverter
                .convertNumberValidatorMessageToString(enumValidatorMessage);
        if (numberValidator.areNumbersAfterValidationAcceptable(enumValidatorMessage)) {
            Ticket ticket = ticketStorage.generateTicket(numbersFromUser);
            return NumberReceiverResultMapper.mapToDto(validatorMessage, ticket);
        }
        return new NumberReceiverResultDto(validatorMessage, null);
    }

    public List<TicketDto> userNumbersForGivenDate(LocalDateTime date) {
        // returns all numbers for given date
        List<Ticket> ticketsFromDate = ticketStorage.getTicketsFromDate(date);
        List<TicketDto> ticketDtos = ListTicketDtoMapper.mapListOfTicketToTicketDto(ticketsFromDate);
        return ticketDtos;
    }
}
