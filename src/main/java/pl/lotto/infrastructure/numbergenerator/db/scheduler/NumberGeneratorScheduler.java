package pl.lotto.infrastructure.numbergenerator.db.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.numbergenerator.NumberGeneratorFacade;
import pl.lotto.numbergenerator.dto.NumberGeneratorResultDto;

@Component
@AllArgsConstructor
public class NumberGeneratorScheduler implements GeneratorNumbers {

    NumberGeneratorFacade numberGeneratorFacade;

    @Scheduled(cron = "${app.generate-Numbers-For-Saturday}")
    public boolean generateNumbers() {
        numberGeneratorFacade.generateNumbersForDate(
                LocalDateTime.of(calcNextSunday(LocalDate.now()), LocalTime.of(12, 0))
        );
        return true;
//        List<Integer> integers = numberGeneratorResultDto.winningNumbers();
//        System.out.println(integers);
//        System.out.println("numberGeneratorResultDtoInTest");
//        return true;
    }

    private static LocalDate calcNextSunday(LocalDate currentDay) {
        return currentDay.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }
}
