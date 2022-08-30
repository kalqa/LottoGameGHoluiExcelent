package pl.lotto.numbergenerator;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
class WinningNumberImpl implements WinningNumberGenerable {

    private final int MAX = 99;
    private final int MIN = 1;
    Random random;


    public List<Integer> generateWinningNumbers() {
        Set<Integer> result = new LinkedHashSet<>();
        while (result.size() < 6) {
            int currentNumber = random.nextInt((MAX - MIN) + 1) + MIN;
            result.add(currentNumber);
        }
        return new ArrayList<>(result);
    }
}
