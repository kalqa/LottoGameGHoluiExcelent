package pl.lotto.infrastructure.numbergenerator.db.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;
import pl.lotto.resultannoucer.ResultAnnouncerFacade;
import pl.lotto.resultannoucer.dto.ResultAnnouncerMessageDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@AllArgsConstructor
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, path = "/generateNumbersForDate")
public class NumberGeneratorController {

    NumberGeneratorFacade numberGeneratorFacade;

    @GetMapping(value = "/{currentDate}")
    public ResponseEntity<NumberGeneratorResultDto> generateNumbersForDate(@PathVariable String currentDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime currentDateToPush = LocalDateTime.parse(currentDate, formatter);
        NumberGeneratorResultDto numberGeneratorResultDto = numberGeneratorFacade.generateNumbersForDate(currentDateToPush);
        if (numberGeneratorResultDto.winningNumbers().size() == 6) {
            return ResponseEntity.ok(numberGeneratorResultDto);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(numberGeneratorResultDto);
    }
}