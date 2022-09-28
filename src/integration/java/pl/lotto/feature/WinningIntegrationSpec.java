package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.lotto.infrastructure.numbergenerator.db.scheduler.GeneratorNumbers;
import pl.lotto.infrastructure.numbergenerator.db.scheduler.NumberGeneratorScheduler;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;
import pl.lotto.infrastructure.resultannouncer.controller.exception.TicketNotFoundException;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;
import pl.lotto.resultannoucer.ResultAnnouncerFacade;
import pl.lotto.resultannoucer.dto.ResultAnnouncerMessageDto;
import pl.lotto.resultchecker.ResultCheckerFacade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

//@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WinningIntegrationSpec extends BaseSpecIntegration implements defaultMethods {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NumberGeneratorFacade numberGeneratorFacade;
    //
    @Autowired
    ResultCheckerFacade resultCheckerFacade;

    @Autowired
    NumberGeneratorScheduler numberGeneratorScheduler;

    @Autowired
    GeneratorNumbers generatorNumbers;

    @Autowired
    ResultAnnouncerFacade resultAnnouncerFacade;

//    # HAPPY PATH 1

//    when user input 6 numbers (1, 2, 3, 4, 5, 6) to POST /inputNumbers on date on 05-07-2022
//    system returns that numbers were correct in range (1-99), unique random ID (userLotteryId) and draw date 06-07-2022
//    system generates random winning number (1, 2, 3, 4, 5, 6) using “lotto” logic for day 06-07-2022
//    user wants to know if won using GET /winners/{userLotteryId}
//    system returns ‘won result’ to user

    @Test
    public void should_user_play_and_win() throws Exception {
        // given
        NumberReceiverRequestDto userNumbers = NumberReceiverRequestDto.builder()
                .clientNumbers(List.of(1, 2, 3, 4, 5, 6))
                .build();
        MvcResult mvcResultOfPostInputNumbers = postInputNumbers(mockMvc, userNumbers);

        // when
        NumberReceiverResultDto numberReceiverResultDto =
                objectMapper.readValue(mvcResultOfPostInputNumbers.getResponse().getContentAsString(), NumberReceiverResultDto.class);

        // then
        assertThat(mvcResultOfPostInputNumbers.getResponse().getStatus()).isEqualTo(200);
        assertThat(numberReceiverResultDto.message()).isEqualTo(List.of("correct message"));
        assertThat(numberReceiverResultDto.ticket().userNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));

        // given
        String userHashCode = numberReceiverResultDto.ticket().hash();
        LocalDateTime currentDate = numberReceiverResultDto.ticket().dateAndTimeNextDraw();

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .until(() -> resultCheckerFacade.winners(currentDate) != null);

//        Awaitility.await()
//                .atMost(3000, TimeUnit.SECONDS)
//                .until(() -> resultCheckerFacade.winners(currentDate) != null);

        MvcResult mvcResultOfGetWinners = getWinners(mockMvc, userHashCode, currentDate);

        ResultAnnouncerMessageDto resultAnnouncerDto =
                objectMapper.readValue(mvcResultOfGetWinners.getResponse().getContentAsString(), ResultAnnouncerMessageDto.class);

        assertThat(mvcResultOfGetWinners.getResponse().getStatus()).isEqualTo(200);
        assertThat(resultAnnouncerDto.userWonInformation()).isEqualTo(true);
    }

    @Test
    public void shouldReturnTicketNotFoundException() {
        String ticketHash = "hash";
        LocalDateTime currentDateTime = LocalDateTime.now();
        Assertions.assertThrows(TicketNotFoundException.class,
                () -> resultCheckerFacade.isUserPresent(ticketHash, currentDateTime));
    }

    @Test
    public void shouldReturnThatNumberWereWrong() throws Exception {
        NumberReceiverRequestDto userNumbers = NumberReceiverRequestDto.builder()
                .clientNumbers(List.of(1, 2, 3, 4, 5, 6, 6))
                .build();
        MvcResult mvcResultOfGetTicket = mockMvc.perform
                        (MockMvcRequestBuilders.post("/inputNumbers")
                                .content(new ObjectMapper().writeValueAsString(userNumbers))
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(mvcResultOfGetTicket.getResponse().getStatus()).isEqualTo(400);
        assertThat(mvcResultOfGetTicket.getResponse().getContentAsString())
                .isEqualTo("you must give exactly six numbers ," +
                        " you must give exactly six not repeatable numbers");
    }

    @Test
    public void shouldReturnThatTicketIsInBaseButUserDidntWin() throws Exception {
        // given
        NumberReceiverRequestDto userNumbers = NumberReceiverRequestDto.builder()
                .clientNumbers(List.of(1, 2, 45, 46, 50, 60))
                .build();
        MvcResult mvcResultOfPostInputNumbers = postInputNumbers(mockMvc, userNumbers);

        NumberReceiverResultDto numberReceiverResultDto =
                objectMapper.readValue(mvcResultOfPostInputNumbers.getResponse().getContentAsString(), NumberReceiverResultDto.class);

        assertThat(mvcResultOfPostInputNumbers.getResponse().getStatus()).isEqualTo(200);
        assertThat(numberReceiverResultDto.message()).isEqualTo(List.of("correct message"));
        assertThat(numberReceiverResultDto.ticket().userNumbers()).isEqualTo(List.of(1, 2, 45, 46, 50, 60));

        String userHashCode = numberReceiverResultDto.ticket().hash();
        LocalDateTime currentDate = numberReceiverResultDto.ticket().dateAndTimeNextDraw();

//        Awaitility.await()
//                .atMost(3, TimeUnit.SECONDS)
//                .until(() -> generatorNumbers.generateNumbers());

        Awaitility.await()
                .pollDelay(Duration.ONE_SECOND)
                .atMost(3, TimeUnit.SECONDS)
                .until(() -> resultCheckerFacade.winners(currentDate) != null);

        MvcResult mvcResultOfGetWinners = getWinners(mockMvc, userHashCode, currentDate);

        ResultAnnouncerMessageDto resultAnnouncerDto =
                objectMapper.readValue(mvcResultOfGetWinners.getResponse().getContentAsString(), ResultAnnouncerMessageDto.class);

        assertThat(mvcResultOfGetWinners.getResponse().getStatus()).isEqualTo(200);
        assertThat(resultAnnouncerDto.userWonInformation()).isEqualTo(false);
    }
}
