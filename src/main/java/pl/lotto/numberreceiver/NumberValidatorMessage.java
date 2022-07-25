package pl.lotto.numberreceiver;

public enum NumberValidatorMessage {
    NUMBER_OUT_OF_RANGE("all numbers should be in range 1-99"),
    MUST_GIVE_SIX_NUMBERS("you must give exactly six numbers");

    final String message;

    NumberValidatorMessage(String message) {
        this.message = message;
    }
}
