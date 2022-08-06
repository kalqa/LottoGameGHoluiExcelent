package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.TicketDto;

import java.util.List;

interface WinnerTicketCheckable {
    List<TicketDto> checkWhichTicketWon();
}
