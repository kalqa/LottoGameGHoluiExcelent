package pl.lotto.numberreceiver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

interface TicketRepository {

    void saveTicket(Ticket ticket, LocalDateTime currentDateAndTime);

    Map<LocalDateTime, Ticket> getAllTickets();

    List<Ticket> getTicketsForGivenDate(LocalDate dateToGet);

    Optional<Ticket> findTicketByHash(String currentTicketHash);

//    void saveForTests(Map<LocalDateTime, List<Ticket>> tickets);

}
