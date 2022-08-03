package pl.lotto.numberreceiver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Ticket> getTicketsForGivenDate(LocalDate dateToGet) {
        return getAllTickets()
                .entrySet()
                .stream()
                .filter(currentElement -> currentElement.getKey().toLocalDate().equals(dateToGet))
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
