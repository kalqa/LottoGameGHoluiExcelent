package pl.lotto.numbergenerator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface WinningNumberGenerable {
    List<Integer> generateWinningNumbers();
}

