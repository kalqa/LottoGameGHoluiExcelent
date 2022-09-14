package pl.lotto.infrastructure.numberreceiver.controller;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class NumberReceiverRequestDto {
    List<Integer> clientNumbers;
}
