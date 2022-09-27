package pl.lotto.numberreceiver;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.UUID;

@Component
class TicketGenerator implements TicketGenerable {

    private static LocalDate calcNextSaturday(LocalDate currentDay) {
        return currentDay.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }

    @Override
    public Ticket generateUserTicket(List<Integer> numbersFromUser) {
        String hash = UUID.randomUUID().toString();
        LocalDateTime theNextSaturday = LocalDateTime.of(calcNextSaturday(LocalDate.now()),
                LocalTime.of(12, 0));
        return Ticket.builder()
                .userNumbers(numbersFromUser)
                .hash(hash)
                .nextDrawDate(theNextSaturday)
                .build();
    }
}
