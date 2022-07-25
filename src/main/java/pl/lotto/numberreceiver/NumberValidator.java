package pl.lotto.numberreceiver;

import java.util.ArrayList;
import java.util.List;
import static pl.lotto.numberreceiver.NumberValidatorMessage.MUST_GIVE_SIX_NUMBERS;
import static pl.lotto.numberreceiver.NumberValidatorMessage.NUMBER_OUT_OF_RANGE;

class NumberValidator {

    public static final int MAX_NUMBERS_FROM_USER = 6;
    List<NumberValidatorMessage> validatorMessages = new ArrayList<>();

    boolean validate(List<Integer> numbersFromUser) {
        if (areExactlySixNumbers(numbersFromUser)) {
            validatorMessages.add(MUST_GIVE_SIX_NUMBERS);
        }
        if (areAllNumbersInRange(numbersFromUser)) {
            validatorMessages.add(NUMBER_OUT_OF_RANGE);
        }
        return validatorMessages.isEmpty();
    }

    private boolean areExactlySixNumbers(List<Integer> numbersFromUser) {
        return numbersFromUser.size() != MAX_NUMBERS_FROM_USER;
    }

    private boolean areAllNumbersInRange(List<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .allMatch(number -> number >= 1 && number <= 99);
    }
}
