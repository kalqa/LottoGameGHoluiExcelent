package pl.lotto.numberreceiver;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static pl.lotto.numberreceiver.NumberValidatorMessage.EVERYTHING_IS_FINE;
import static pl.lotto.numberreceiver.NumberValidatorMessage.MUST_GIVE_NONE_REPEATABLE_NUMBERS;
import static pl.lotto.numberreceiver.NumberValidatorMessage.MUST_GIVE_SIX_NUMBERS;
import static pl.lotto.numberreceiver.NumberValidatorMessage.NUMBER_OUT_OF_RANGE;

class NumberValidator {

    public static final int MAX_NUMBERS_FROM_USER = 6;
    public static final int MIN_GIVEN_NUMBER = 1;
    public static final int MAX_GIVEN_NUMBER = 99;
    public static final int FIRST_INDEX_IN_LIST = 0;

    List<NumberValidatorMessage> validate(List<Integer> numbersFromUser) {
        List<NumberValidatorMessage> validatorMessages = new ArrayList<>();
        List<NumberValidatorMessage> numberValidatorMessages = messagesAdder(numbersFromUser);
        if (numberValidatorMessages.isEmpty()) {
            validatorMessages.add(EVERYTHING_IS_FINE);
        } else {
            validatorMessages.addAll(numberValidatorMessages);
        }
        return validatorMessages;
    }

    private List<NumberValidatorMessage> messagesAdder(List<Integer> numbersFromUser) {
        List<NumberValidatorMessage> result = new ArrayList<>();
        if (areExactlySixNumbers(numbersFromUser)) {
            result.add(MUST_GIVE_SIX_NUMBERS);
        }
        if (areAllNumbersInRange(numbersFromUser)) {
            result.add(NUMBER_OUT_OF_RANGE);
        }
        if (areAllNumbersNotRepeatable(numbersFromUser)) {
            result.add(MUST_GIVE_NONE_REPEATABLE_NUMBERS);
        }
        return result;
    }

    public boolean areNumbersAfterValidationAcceptable(List<NumberValidatorMessage> validatorMessage) {
        return validatorMessage.size() == MIN_GIVEN_NUMBER &&
                EVERYTHING_IS_FINE.equals(validatorMessage.get(FIRST_INDEX_IN_LIST));
    }

    private boolean areExactlySixNumbers(List<Integer> numbersFromUser) {
        return numbersFromUser.size() != MAX_NUMBERS_FROM_USER;
    }

    private boolean areAllNumbersInRange(List<Integer> numbersFromUser) {
        return !(numbersFromUser.stream()
                .allMatch(number -> number >= MIN_GIVEN_NUMBER && number <= MAX_GIVEN_NUMBER));
    }

    private boolean areAllNumbersNotRepeatable(List<Integer> numbersFromUser) {
        Set<Integer> set = new LinkedHashSet<>(numbersFromUser);
        return numbersFromUser.size() != set.size();
    }
}
