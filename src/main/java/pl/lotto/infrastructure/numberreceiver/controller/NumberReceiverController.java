package pl.lotto.infrastructure.numberreceiver.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

@RestController
@AllArgsConstructor
public class NumberReceiverController {

    NumberReceiverFacade facade;

    @GetMapping("/inputNumbers")
    public ResponseEntity<NumberReceiverResultDto> inputNumbers(@RequestBody NumberReceiverRequestDto request) {
        NumberReceiverResultDto numberReceiverResultDto = facade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
        if (numberReceiverResultDto.message().contains("you must give exactly six numbers")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(numberReceiverResultDto);
    }
}
