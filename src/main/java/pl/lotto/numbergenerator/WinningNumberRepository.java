package pl.lotto.numbergenerator;

import java.time.LocalDate;
import java.util.List;

interface WinningNumberRepository {

    void save(List<Integer> numbers, LocalDate currentDate);

    List<Integer> getWinningNumbersForGivenDate(LocalDate dateToGet);
}
