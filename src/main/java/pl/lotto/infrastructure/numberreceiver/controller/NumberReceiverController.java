package pl.lotto.infrastructure.numberreceiver.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body(numberReceiverResultDto);
        }
        return ResponseEntity.ok(numberReceiverResultDto);
    }
}
