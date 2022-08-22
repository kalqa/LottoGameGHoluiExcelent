package pl.lotto.infrastructure.numberreceiver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@RestController
@AllArgsConstructor
@RequestMapping("/numbers")
public class NumberReceiverController {

    NumberReceiverFacade numberReceiverFacade;

    @PostMapping
    public void inputNumbers() {
        System.out.println("hello world");
//        numberReceiverFacade.inputNumbers()
    }

}
