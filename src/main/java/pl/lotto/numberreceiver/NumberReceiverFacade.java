package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static pl.lotto.numberreceiver.NumberValidatorMessage.EVERYTHING_IS_FINE;

@AllArgsConstructor
public class NumberReceiverFacade {

    NumberValidator numberValidator;
    TicketRepository ticketRepositoryImpl;

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        Optional<NumberValidatorMessage> validatorMessage = numberValidator.validate(numbersFromUser);
        if (areNumbersAfterValidationAcceptable(validatorMessage)) {
            Ticket uniqueUserTicket = TicketGenerator.generateUserTicket(numbersFromUser, ticketRepositoryImpl);
//            getUserTicket(uniqueUserTicket);
            return new NumberReceiverResultDto(validatorMessage.get().message, uniqueUserTicket);
        }
        return new NumberReceiverResultDto(validatorMessage.get().message);
    }

//    public Ticket getUserTicket(Ticket userTicket) {
//        return userTicket;
//    }

    private boolean areNumbersAfterValidationAcceptable(Optional<NumberValidatorMessage> validatorMessage) {
        return validatorMessage.filter(EVERYTHING_IS_FINE::equals).isPresent();
    }

    public void userNumbersForGivenDate(LocalDateTime date) {
        // returns all numbers for given date
        return;
    }
}