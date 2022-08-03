package pl.lotto.numbergenerator;

import java.util.List;

public interface WinningNumberRepository {


    void save(List<Integer> numbers);
}
