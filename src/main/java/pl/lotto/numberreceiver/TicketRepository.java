package pl.lotto.numberreceiver;

import java.util.Optional;
import java.util.Set;

interface TicketRepository {

    void saveTicket(Ticket ticket);

    Optional<Ticket> findTicketByHash(String currentTicketHash);

}
