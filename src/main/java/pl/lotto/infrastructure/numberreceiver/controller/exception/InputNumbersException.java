package pl.lotto.infrastructure.numberreceiver.controller.exception;


public class InputNumbersException extends RuntimeException {
    public InputNumbersException(String textError) {
        super(textError);
    }
}
