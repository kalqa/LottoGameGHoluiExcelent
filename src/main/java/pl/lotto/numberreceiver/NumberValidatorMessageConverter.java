package pl.lotto.numberreceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class NumberValidatorMessageConverter {
    List<String> convertNumberValidatorMessageToString(List<NumberValidatorMessage> validatorMessages) {
        return validatorMessages.stream()
                .map(numberValidatorMessage -> numberValidatorMessage.message)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
