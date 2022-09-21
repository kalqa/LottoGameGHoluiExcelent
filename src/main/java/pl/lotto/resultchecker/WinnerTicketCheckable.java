package pl.lotto.resultchecker;

import org.springframework.stereotype.Component;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

interface WinnerTicketCheckable {
    List<TicketDto> checkWhichTicketWon(List<TicketDto> tickets, List<Integer> winnerNumbers);
}
