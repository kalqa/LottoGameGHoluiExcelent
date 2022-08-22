package pl.lotto.resultannoucer;

import lombok.AllArgsConstructor;
import pl.lotto.resultannoucer.dto.ResultAnnouncerMessageDto;
import pl.lotto.resultchecker.ResultCheckerFacade;

@AllArgsConstructor
public class ResultAnnouncerFacade {

    ResultCheckerFacade resultCheckerFacade;

    public ResultAnnouncerMessageDto winner(String userId) {
        boolean isUserWon = resultCheckerFacade.winner(userId);
        return new ResultAnnouncerMessageDto(isUserWon);
    }
}
