package pl.lotto.numberreceiver;

enum NumberValidatorMessage {
    NUMBER_OUT_OF_RANGE("all numbers should be in range 1-99"),
    MUST_GIVE_SIX_NUMBERS("you must give exactly six numbers"),
    MUST_GIVE_NONE_REPEATABLE_NUMBERS("you must give exactly six not repeatable numbers"),
    EVERYTHING_IS_FINE("correct message");
    final String message;

    NumberValidatorMessage(String message) {
        this.message = message;
    }
}
