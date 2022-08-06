package pl.lotto.numbergenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

class WinningNumberRepositoryTestImpl implements WinningNumberRepository {
    Map<LocalDateTime, List<Integer>> winningNumbers;

    public WinningNumberRepositoryTestImpl(Map<LocalDateTime, List<Integer>> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    @Override
    public void save(List<Integer> numbers, LocalDateTime currentDate) {
        winningNumbers.put(currentDate, numbers);
    }

    @Override
    public List<Integer> getWinningNumbersForGivenDate(LocalDateTime dateToGet) {
        return winningNumbers.get(dateToGet);
    }
}
