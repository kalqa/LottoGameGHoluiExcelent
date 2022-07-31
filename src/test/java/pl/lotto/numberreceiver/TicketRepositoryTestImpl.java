package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

class TicketRepositoryTestImpl implements TicketRepository {
    Map<LocalDateTime, Ticket> tickets;

    public TicketRepositoryTestImpl(Map<LocalDateTime, Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketRepositoryTestImpl() {

    }

    @Override
    public void saveTicket(Ticket ticket, LocalDateTime currentDateAndTime) {
        tickets.put(currentDateAndTime, ticket);
    }

    @Override
    public Map<LocalDateTime, Ticket> getAllTickets() {
        return tickets;
    }


    public Optional<Ticket> findTicketByHash(String currentTicketHash) {
        return null;
        //        return tickets
//                .stream()
//                .filter(ticket -> currentTicketHash.equals(ticket.getHash()))
//                .findFirst();
    }


}
