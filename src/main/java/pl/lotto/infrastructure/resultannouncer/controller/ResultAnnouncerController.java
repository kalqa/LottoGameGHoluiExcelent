package pl.lotto.infrastructure.resultannouncer.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;
import pl.lotto.infrastructure.resultannouncer.controller.exception.TicketNotFoundException;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultannoucer.ResultAnnouncerFacade;
import pl.lotto.resultannoucer.dto.ResultAnnouncerMessageDto;

@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/winners")
@RestController
@AllArgsConstructor
public class ResultAnnouncerController {

    ResultAnnouncerFacade announcerFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResultAnnouncerMessageDto> winners(@PathVariable String id) {
        ResultAnnouncerMessageDto winner = announcerFacade.winner(id);
        if (winner.userWonInformation()) {
            return ResponseEntity.ok(announcerFacade.winner(id));
        }
        throw new TicketNotFoundException(id);
    }
}