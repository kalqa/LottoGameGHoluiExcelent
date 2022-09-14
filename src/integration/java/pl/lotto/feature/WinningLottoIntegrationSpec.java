package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.lotto.BaseSpecIntegration;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;
import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ActiveProfiles("test")
@AutoConfigureMockMvc
public class WinningLottoIntegrationSpec extends BaseSpecIntegration {


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

//    # HAPPY PATH 1
//
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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/inputNumbers")
                        .content(new ObjectMapper().writeValueAsString(userNumbers))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
//                .andExpect(status().is(200))
//                .andExpect(jsonPath("$"))
        NumberReceiverResultDto numberReceiverResultDto =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), NumberReceiverResultDto.class);

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        assertThat(numberReceiverResultDto.message()).isEqualTo(List.of("correct message"));
        assertThat(numberReceiverResultDto.ticket().userNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));

        String userHashCode = numberReceiverResultDto.ticket().hash();


    }
}
