package pl.lotto.numbergenerator.dto;

import java.util.List;

public record NumberGeneratorResultDto(List<Integer> winningNumbers) {
    @Override
    public List<Integer> winningNumbers() {
        return winningNumbers;
    }
}
