package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

public class NumberReceiverFacade {

    NumberValidator numberValidator;

    NumberReceiverFacade(NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        if (!numberValidator.validate(numbersFromUser)) {
            return new NumberReceiverResultDto("you must give exactly six numbers");
        }
        return new NumberReceiverResultDto("correct message");
    }

    public void userNumbersForGivenDate(LocalDateTime date) {
        // returns all numbers for given date
        return;
    }
}
