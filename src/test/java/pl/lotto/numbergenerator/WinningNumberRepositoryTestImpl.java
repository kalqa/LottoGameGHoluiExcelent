package pl.lotto.numbergenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

class WinningNumberRepositoryTestImpl implements WinningNumberRepository {
    Map<LocalDate, List<Integer>> winningNumbers;

    public WinningNumberRepositoryTestImpl(Map<LocalDate, List<Integer>> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    @Override
    public void save(List<Integer> numbers, LocalDate currentDate) {
        winningNumbers.put(currentDate, numbers);
    }

    @Override
    public List<Integer> getWinningNumbersForGivenDate(LocalDate dateToGet) {
        return winningNumbers.get(dateToGet);
    }
}
