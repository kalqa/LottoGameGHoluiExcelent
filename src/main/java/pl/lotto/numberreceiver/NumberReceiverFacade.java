package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class NumberReceiverFacade {

    NumberValidator numberValidator;
    NumberValidatorMessageConverter numberValidatorMessageConverter;
    TicketAbcd ticketAbcd;

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        List<NumberValidatorMessage> enumValidatorMessage = numberValidator.validate(numbersFromUser);
        List<String> validatorMessage = numberValidatorMessageConverter.convertNumberValidatorMessageToString(enumValidatorMessage);
        if (numberValidator.areNumbersAfterValidationAcceptable(enumValidatorMessage)) {
            Ticket ticket = ticketAbcd.generateTicket(numbersFromUser);
            return NumberReceiverResultMapper.mapToDto(validatorMessage, ticket);
        }
        return new NumberReceiverResultDto(validatorMessage, null);
    }

    public void userNumbersForGivenDate(LocalDateTime date) {
        // returns all numbers for given date
        return;
    }
}
