package pl.lotto.feature;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import pl.lotto.BaseSpecIntegration;

public class WinningLottoIntegrationSpec extends BaseSpecIntegration {


//    # HAPPY PATH 1
//
//    when user input 6 numbers (1, 2, 3, 4, 5, 6) to POST /inputNumbers on date on 05-07-2022
//    system returns that numbers were correct in range (1-99), unique random ID (userLotteryId) and draw date 06-07-2022
//    system generates random winning number (1, 2, 3, 4, 5, 6) using “lotto” logic for day 06-07-2022
//    user wants to know if won using GET /winners/{userLotteryId}
//    system returns ‘won result’ to user

    @Test
    public void should_user_play_and_win() {
        // given

        // when
        ResponseEntity<String> forEntity = restTemplate.postForEntity("/inputNumberss", String.class);

        // then
        System.out.println(forEntity);


        // given

        // when
        ResponseEntity<String> forEntity = restTemplate.getForEntity("/winners/", String.class);

        // then
        System.out.println(forEntity);

    }
}
