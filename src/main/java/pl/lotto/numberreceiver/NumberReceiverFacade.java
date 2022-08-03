package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
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

    public List<Ticket> userNumbersForGivenDate(LocalDate date) {
        // returns all numbers for given date
        return ticketStorage.getTicketsFromDate(date);
    }
}
