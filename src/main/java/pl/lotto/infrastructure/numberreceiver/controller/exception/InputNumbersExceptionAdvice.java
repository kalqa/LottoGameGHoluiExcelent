package pl.lotto.infrastructure.numberreceiver.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.lotto.infrastructure.resultannouncer.controller.exception.TicketNotFoundException;

@ControllerAdvice
public class InputNumbersExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(InputNumbersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String InputNumbersErrorHandler(InputNumbersException InputNumbersException) {
        return InputNumbersException.getMessage();
    }
}
