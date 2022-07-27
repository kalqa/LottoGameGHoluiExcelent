package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.UUID;

class TicketGenerator implements TicketGenerable {

    @Override
    public Ticket generateUserTicket(List<Integer> numbersFromUser) {
        String hash = UUID.randomUUID().toString();
        LocalDateTime theNextSunday = LocalDateTime.of(calcNextSunday(LocalDate.now()),
                LocalTime.of(12, 0));
        return Ticket.builder()
                .userNumbers(numbersFromUser)
                .hash(hash)
                .dateAndTimeNextDraw(theNextSunday)
                .build();
    }

    private static LocalDate calcNextSunday(LocalDate currentDay) {
        return currentDay.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }
}
