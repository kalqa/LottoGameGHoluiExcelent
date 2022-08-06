package pl.lotto.numbergenerator;

import pl.lotto.numberreceiver.numbergenerator.WinningNumberGenerable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 class WinningNumberGenerableTestImpl implements WinningNumberGenerable {
    @Override
    public List<Integer> generateWinningNumbers() {
        return new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    }
}
