package pl.lotto.numberreceiver;

import java.util.List;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

public class NumberReceiverFacade {

    public NumberReceiverResultDto inputNumbers(List<Integer> numbersFromUser) {
        NumberReceiverResultDto result = new NumberReceiverResultDto("correct message");
        return result;
    }
}
