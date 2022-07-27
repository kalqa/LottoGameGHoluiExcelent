package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

class TicketGeneratorTestImpl implements TicketGenerable {

    List<Integer> numbersFromUser;

    TicketGeneratorTestImpl(List<Integer> numbersFromUser) {
        this.numbersFromUser = numbersFromUser;
    }

    @Override
    public Ticket generateUserTicket(List<Integer> numbersFromUser) {
        return new Ticket(
                "testHash",
                this.numbersFromUser,
                LocalDateTime.of(2022, 7, 10, 12, 0)
        );
    }

    public saveForTests(){
        numbersFromUser.add()
    }
}
