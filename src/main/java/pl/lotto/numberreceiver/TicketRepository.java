package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

interface TicketRepository {

    void saveTicket(Ticket ticket);

    Map<String, Ticket> getAllTickets();

    List<Ticket> getTicketsForGivenDate(LocalDateTime dateToGet);

    Optional<Ticket> findTicketByHash(String currentTicketHash);

//    void saveForTests(Map<LocalDateTime, List<Ticket>> tickets);

}
