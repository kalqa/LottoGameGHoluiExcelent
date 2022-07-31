package pl.lotto.numberreceiver;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
class TicketAbcd {

    TicketGenerable ticketGenerator;
    TicketRepository ticketRepository;

    public Ticket generateTicket(List<Integer> numbersFromUser) {
        Ticket uniqueUserTicket = ticketGenerator.generateUserTicket(numbersFromUser);
        ticketRepository.saveTicket(uniqueUserTicket, LocalDateTime.now());
        return uniqueUserTicket;
    }

    public List<Ticket> getTicketsFromDate(LocalDate dateToGet) {
        return ticketRepository
                .getAllTickets()
                .entrySet()
                .stream()
                .filter(currentElement -> currentElement.getKey().toLocalDate().equals(dateToGet))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}