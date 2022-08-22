package pl.lotto.infrastructure.numbergenerator.db;

import java.time.LocalDateTime;
import java.util.List;
import pl.lotto.numbergenerator.WinningNumberRepository;

public class WinningNumberRepositoryImpl implements WinningNumberRepository {

    @Override
    public void save(List<Integer> numbers, LocalDateTime currentDate) {

    }

    @Override
    public List<Integer> getWinningNumbersForGivenDate(LocalDateTime dateToGet) {
        return null;
    }
}
