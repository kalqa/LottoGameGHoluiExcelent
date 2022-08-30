package pl.lotto.numbergenerator;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface WinningNumberGenerable {
    List<Integer> generateWinningNumbers();
}

