package pl.lotto.infrastructure.resultannouncer.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;
import pl.lotto.infrastructure.resultannouncer.controller.exception.TicketNotFoundException;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultannoucer.ResultAnnouncerFacade;
import pl.lotto.resultannoucer.dto.ResultAnnouncerMessageDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/winners")
@RestController
@AllArgsConstructor
public class ResultAnnouncerController {

    ResultAnnouncerFacade announcerFacade;

    @GetMapping("/winners/{id}/{dateToGet}")
    public ResponseEntity<ResultAnnouncerMessageDto> winners(@PathVariable String id, @PathVariable String dateToGet) {
        LocalDateTime date = LocalDateTime.parse(dateToGet);
        ResultAnnouncerMessageDto winner = announcerFacade.winner(id, date);
        if (winner.userWonInformation()) {
            return ResponseEntity.ok(winner);
        }
        throw new TicketNotFoundException(id);
    }

//    @GetMapping("/winners" + "{/id}" + "/{dateToGet}")
//    public ResponseEntity<ResultAnnouncerMessageDto> winners(
//            @PathVariable LocalDateTime dateToGet) {
//        ResultAnnouncerMessageDto winner = announcerFacade.winner("id", dateToGet);
//        if (winner.userWonInformation()) {
//            return ResponseEntity.ok(winner);
//        }
//        throw new TicketNotFoundException("id");
//    }
}