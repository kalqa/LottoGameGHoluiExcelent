package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
class TicketStorage {

    TicketGenerable ticketGenerator;
    TicketRepository ticketRepository;

    public Ticket generateTicket(List<Integer> numbersFromUser) {
        Ticket uniqueUserTicket = ticketGenerator.generateUserTicket(numbersFromUser);
        ticketRepository.saveTicket(uniqueUserTicket);
        return uniqueUserTicket;
    }

    public List<Ticket> getTicketsFromDate(LocalDateTime dateToGet) {
        return ticketRepository.getTicketsForGivenDate(dateToGet);
    }

}
