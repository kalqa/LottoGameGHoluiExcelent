package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import static pl.lotto.numberreceiver.NumberValidatorMessage.EVERYTHING_IS_FINE;

@AllArgsConstructor
public class NumberReceiverFacade {

    NumberValidator numberValidator;
    TicketAbcd ticketAbcd;

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        Optional<NumberValidatorMessage> validatorMessage = numberValidator.validate(numbersFromUser);
        if (numberValidator.areNumbersAfterValidationAcceptable(validatorMessage)) {
            Ticket ticket = ticketAbcd.generateTicket(numbersFromUser);
//            getUserTicket(uniqueUserTicket);

            return NumberReceiverResultMapper.mapToDto(validatorMessage.get().message, ticket);
//            return new NumberReceiverResultDto(validatorMessage.get().message, uniqueUserTicket);
        }
        return new NumberReceiverResultDto(validatorMessage.get().message, null);
    }

//    public Ticket getUserTicket(Ticket userTicket) {
//        return userTicket;
//    }



    public void userNumbersForGivenDate(LocalDateTime date) {
        // returns all numbers for given date
        return;
    }
}
