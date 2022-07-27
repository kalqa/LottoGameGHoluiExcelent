package pl.lotto.numberreceiver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static pl.lotto.numberreceiver.NumberValidatorMessage.EVERYTHING_IS_FINE;
import static pl.lotto.numberreceiver.NumberValidatorMessage.MUST_GIVE_NONE_REPEATABLE_NUMBERS;
import static pl.lotto.numberreceiver.NumberValidatorMessage.MUST_GIVE_SIX_NUMBERS;
import static pl.lotto.numberreceiver.NumberValidatorMessage.NUMBER_OUT_OF_RANGE;

class NumberValidator {

    public static final int MAX_NUMBERS_FROM_USER = 6;
    public static final int MIN_GIVEN_NUMBER = 1;
    public static final int MAX_GIVEN_NUMBER = 99;

    //    List<NumberValidatorMessage> validatorMessages = new ArrayList<>();
    Optional<NumberValidatorMessage> validate(List<Integer> numbersFromUser) {
        Optional<NumberValidatorMessage> result;
        if (areExactlySixNumbers(numbersFromUser)) {
//            validatorMessages.add(MUST_GIVE_SIX_NUMBERS);
            result = Optional.of(MUST_GIVE_SIX_NUMBERS);
        } else if (areAllNumbersInRange(numbersFromUser)) {
//            validatorMessages.add(NUMBER_OUT_OF_RANGE);
            result = Optional.of(NUMBER_OUT_OF_RANGE);
        } else if (areAllNumbersNotRepeatable(numbersFromUser)) {
//            validatorMessages.add(MUST_GIVE_NONE_REPEATABLE_NUMBERS);
            result = Optional.of(MUST_GIVE_NONE_REPEATABLE_NUMBERS);
        } else {
            result = Optional.of(EVERYTHING_IS_FINE);
        }
        return result;
    }


    private boolean areExactlySixNumbers(List<Integer> numbersFromUser) {
        return numbersFromUser.size() != MAX_NUMBERS_FROM_USER;
    }

    private boolean areAllNumbersInRange(List<Integer> numbersFromUser) {
        return !(numbersFromUser.stream()
                .allMatch(number -> number >= MIN_GIVEN_NUMBER && number <= MAX_GIVEN_NUMBER));
    }

    private boolean areAllNumbersNotRepeatable(List<Integer> numbersFromUser) {
        return new HashSet<>(numbersFromUser).size() < MAX_NUMBERS_FROM_USER;
    }
}
