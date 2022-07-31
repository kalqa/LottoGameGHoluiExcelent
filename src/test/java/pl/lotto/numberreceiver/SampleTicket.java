package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

import pl.lotto.numberreceiver.dto.TicketDto;

interface SampleTicket {

    default TicketDto sampleTicket(List<Integer> expectedUserNumbers, String expectedHash, LocalDateTime expectedDrawDate) {
        return new TicketDto(
                expectedHash,
                expectedUserNumbers,
                expectedDrawDate
        );
    }

    default TicketDto sampleTicketDtoWithTestHash(List<Integer> expectedUserNumbers, LocalDateTime expectedDrawDate) {
        return new TicketDto(
                "testHash",
                expectedUserNumbers,
                expectedDrawDate
        );
    }

    default Ticket sampleTicketWithTestHash(List<Integer> expectedUserNumbers, LocalDateTime expectedDrawDate) {
        return new Ticket(
                "testHash",
                expectedUserNumbers,
                expectedDrawDate
        );
    }

}
