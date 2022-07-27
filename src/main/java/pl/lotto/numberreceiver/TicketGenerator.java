package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.UUID;

class TicketGenerator {

    public static Ticket generateUserTicket(List<Integer> numbersFromUser, TicketRepository ticketRepositoryImpl) {
        String hash = UUID.randomUUID().toString();
        LocalDateTime theNextSunday = LocalDateTime.of(calcNextSunday(LocalDate.now()),
                LocalTime.of(12, 0));
        Ticket ticket = Ticket.builder()
                .userNumbers(numbersFromUser)
                .hash(hash)
                .dateAndTimeNextDraw(theNextSunday)
                .build();
        ticketRepositoryImpl.saveTicket(ticket);
        return ticket;
    }

    private static LocalDate calcNextSunday(LocalDate currentDay) {
        return currentDay.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }
}
