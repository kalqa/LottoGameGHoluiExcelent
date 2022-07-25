package pl.lotto.numberreceiver;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeSpec {

    @Test
    public void should_return_correct_message_when_user_inputed_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("correct message");
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message when user inputed less than six numbers")
    public void should_return_failed_message_when_user_inputed_less_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("you must give exactly six numbers");
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message when user inputed more than six numbers")
    public void should_return_failed_message_when_user_inputed_more_than_six_numbers() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("you must give exactly six numbers");
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("should return failed message when user inputed number out of range")
    public void should_return_failed_message_when_user_inputed_number_out_of_range() {
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverConfiguration().buildModuleForTests();
        List<Integer> numbersFromUser = Arrays.asList(1, 2, 300, 4, 5, 6);

        // when
        NumberReceiverResultDto actualResult = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        NumberReceiverResultDto expectedResult = new NumberReceiverResultDto("all numbers should be in range 1-99");
        assertThat(actualResult).isEqualTo(expectedResult);
    }

}
