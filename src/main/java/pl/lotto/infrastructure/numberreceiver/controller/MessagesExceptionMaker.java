package pl.lotto.infrastructure.numberreceiver.controller;

import pl.lotto.numberreceiver.dto.NumberReceiverResultDto;

class MessagesExceptionMaker {

    static String makeMessage(NumberReceiverResultDto numberReceiverResultDto) {
        return numberReceiverResultDto.message().stream()
                .reduce((currentText, nextText) -> currentText + " , " + nextText)
                .orElse("sth got wrong please contact with man who made this app");
    }
}
