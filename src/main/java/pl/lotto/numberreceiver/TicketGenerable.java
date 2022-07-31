package pl.lotto.numberreceiver;

import java.util.List;

interface TicketGenerable {

    Ticket generateUserTicket(List<Integer> numbersFromUser);

}
