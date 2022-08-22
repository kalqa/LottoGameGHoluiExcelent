package pl.lotto.resultannouncer;

import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultannoucer.ResultAnnouncerConfiguration;
import pl.lotto.resultannoucer.ResultAnnouncerFacade;
import pl.lotto.resultannoucer.dto.ResultAnnouncerMessageDto;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ResultAnnouncerFacadeSpec {

    @Test
    void shouldReturnThatUserWon() {

        //given

        List<TicketDto> ticketDtos = new ArrayList<>(Arrays.asList(
                new TicketDto("hash1", List.of(1, 2, 3, 4, 5, 6), LocalDateTime.of(1, 1, 1, 1, 1)),
                new TicketDto("hash2", List.of(1, 2, 3, 4, 5, 6), LocalDateTime.of(1, 1, 1, 1, 1)),
                new TicketDto("hash3", List.of(1, 2, 3, 4, 5, 6), LocalDateTime.of(1, 1, 1, 1, 1))
        ));
        ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);
        WinnersTicketDataBaseTestImpl winnersTicketDataBaseTest = new WinnersTicketDataBaseTestImpl(ticketDtos);
        given(resultCheckerFacade.getWinnersTicketDataBase()).willReturn(winnersTicketDataBaseTest);

        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration()
                .buildModuleForTest(resultCheckerFacade);


        //when
        String hash1 = "hash1";
        given(resultCheckerFacade.winner(hash1)).willCallRealMethod();
        ResultAnnouncerMessageDto actualResultAnnouncerMessageDto = resultAnnouncerFacade.winner(hash1);

        // then
        boolean expected = true;
        assertThat(actualResultAnnouncerMessageDto.userWonInformation()).isEqualTo(expected);

    }

    @Test
    void shouldReturnThatUserNotWon() {
        //given
        List<TicketDto> ticketDtos = new ArrayList<>(Arrays.asList(
                new TicketDto("hash1", List.of(1, 2, 3, 4, 5, 6), LocalDateTime.of(1, 1, 1, 1, 1)),
                new TicketDto("hash2", List.of(1, 2, 3, 4, 5, 6), LocalDateTime.of(1, 1, 1, 1, 1)),
                new TicketDto("hash3", List.of(1, 2, 3, 4, 5, 6), LocalDateTime.of(1, 1, 1, 1, 1))
        ));
        ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);
        WinnersTicketDataBaseTestImpl winnersTicketDataBaseTest = new WinnersTicketDataBaseTestImpl(ticketDtos);
        given(resultCheckerFacade.getWinnersTicketDataBase()).willReturn(winnersTicketDataBaseTest);

        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration()
                .buildModuleForTest(resultCheckerFacade);


        //when
        String hash1 = "hash4";
        given(resultCheckerFacade.winner(hash1)).willCallRealMethod();
        ResultAnnouncerMessageDto actualResultAnnouncerMessageDto = resultAnnouncerFacade.winner(hash1);

        // then
        boolean expected = false;
        assertThat(actualResultAnnouncerMessageDto.userWonInformation()).isEqualTo(expected);

    }
}