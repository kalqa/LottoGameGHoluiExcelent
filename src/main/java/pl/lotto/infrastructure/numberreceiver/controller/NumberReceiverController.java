package pl.lotto.infrastructure.numberreceiver.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.infrastructure.numberreceiver.controller.exception.InputNumbersException;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

@RestController
@AllArgsConstructor
public class NumberReceiverController {

    NumberReceiverFacade facade;

    @PostMapping("/inputNumbers")
    public ResponseEntity<NumberReceiverResultDto> inputNumbers(@RequestBody NumberReceiverRequestDto request) {
        NumberReceiverResultDto numberReceiverResultDto = facade.inputNumbers(request.getClientNumbers());
        if (!numberReceiverResultDto.message().contains("correct message")) {
            throw new InputNumbersException(MessagesExceptionMaker.makeMessage(numberReceiverResultDto));
        }
        return ResponseEntity.ok(numberReceiverResultDto);
    }
}