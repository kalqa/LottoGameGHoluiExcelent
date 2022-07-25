package pl.lotto.numberreceiver;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeSpec {

    @Test
    public void should_return_correct_message_when_user_inputed_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("correct message");
        assertThat(actualResult).isEqualTo(expectedResult);
    }

}
