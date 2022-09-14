package pl.lotto.infrastructure.resultannouncer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultannoucer.ResultAnnouncerFacade;

public class ResultAnnouncerController {

    ResultAnnouncerFacade announcerFacade;

//    @GetMapping("/inputNumbers")
//    public ResponseEntity<NumberReceiverResultDto> inputNumbers() {
//        NumberReceiverResultDto numberReceiverResultDto = announcerFacade.winner(request.getClientNumbers());
//        if (!numberReceiverResultDto.message().contains("correct message")) {
//            return new ResponseEntity<>(numberReceiverResultDto, HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<>(numberReceiverResultDto, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@RequestParam("id") String id) {
        return ResponseEntity.ok(announcerFacade.winner(id));
    }

}
