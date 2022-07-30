package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class TicketAbcd {

    TicketGenerable ticketGenerator;
    TicketRepository ticketRepository;

    public Ticket generateTicket(List<Integer> numbersFromUser) {
        Ticket uniqueUserTicket = ticketGenerator.generateUserTicket(numbersFromUser);
        ticketRepository.saveTicket(uniqueUserTicket);
        return uniqueUserTicket;
    }

}