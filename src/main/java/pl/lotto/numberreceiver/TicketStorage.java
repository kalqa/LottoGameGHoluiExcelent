package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
class TicketStorage {

    TicketGenerable ticketGenerator;
    TicketRepository ticketRepository;

    public Ticket generateTicket(List<Integer> numbersFromUser) {
        Ticket uniqueUserTicket = ticketGenerator.generateUserTicket(numbersFromUser);
        ticketRepository.save(uniqueUserTicket);
//        Optional<Ticket> ticketByHash = ticketRepository.findTicketByHash(uniqueUserTicket.hash);
//        System.out.println(ticketByHash);
        return uniqueUserTicket;
    }

    public List<Ticket> getTicketsFromDate(LocalDateTime dateToGet) {
        List<Ticket> allByNextDrawDate = ticketRepository.findTicketsByNextDrawDate(dateToGet);
        return allByNextDrawDate;
    }

}
