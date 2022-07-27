package pl.lotto.numberreceiver;

import java.util.List;

public interface TicketGenerable {

    Ticket generateUserTicket(List<Integer> numbersFromUser);
}
