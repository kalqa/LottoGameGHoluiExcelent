package pl.lotto.feature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.lotto.infrastructure.numberreceiver.controller.NumberReceiverRequestDto;

import java.time.LocalDateTime;

interface defaultMethods {
    default MvcResult postInputNumbers(MockMvc mockMvc, NumberReceiverRequestDto userNumbers) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/inputNumbers")
                        .content(new ObjectMapper().writeValueAsString(userNumbers))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        return mvcResult;
    }

    default MvcResult getWinners(MockMvc mockMvc, String userHashCode, LocalDateTime currentDate) throws Exception {
        MvcResult mvcResultOfGetWinners = mockMvc.perform(
                        MockMvcRequestBuilders.get("/winners/" + userHashCode + "/" + currentDate.toString())
//                        MockMvcRequestBuilders.get("/winners/" + userHashCode + "/" + currentDate)
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        return mvcResultOfGetWinners;
    }
}
