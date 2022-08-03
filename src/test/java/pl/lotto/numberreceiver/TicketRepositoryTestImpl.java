package pl.lotto.numberreceiver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class TicketRepositoryTestImpl implements TicketRepository {
    Map<String, Ticket> tickets;

    public TicketRepositoryTestImpl(Map<String, Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketRepositoryTestImpl() {
    }

    @Override
    public void saveTicket(Ticket ticket) {
        tickets.put(ticket.hash, ticket);
    }

    @Override
    public Map<String, Ticket> getAllTickets() {
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsForGivenDate(LocalDateTime drawDate) {
        return getAllTickets()
                .entrySet()
                .stream()
                .filter(currentElement -> currentElement.getValue().dateAndTimeNextDraw.equals(drawDate))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public Optional<Ticket> findTicketByHash(String currentTicketHash) {
        return tickets
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(currentTicket -> currentTicket.getHash().equals(currentTicketHash))
                .findFirst();
    }


}
