package pl.lotto.resultannoucer;

import lombok.AllArgsConstructor;
import pl.lotto.resultannoucer.dto.ResultAnnouncerMessageDto;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ResultAnnouncerFacade {

    ResultCheckerFacade resultCheckerFacade;

    public ResultAnnouncerMessageDto winner(String userId, LocalDateTime dateToGetWinnersTicket) {
        boolean isUserWon = resultCheckerFacade.winner(userId, dateToGetWinnersTicket);
        return new ResultAnnouncerMessageDto(isUserWon);
    }
}
