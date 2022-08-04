package pl.lotto.numbergenerator;

public class NumberGeneratorConfiguration {
    NumberGeneratorFacade buildModuleForClient(WinningNumberGenerable winningNumberGenerable,
                                               WinningNumberRepository winningNumberRepository) {
        return new NumberGeneratorFacade(winningNumberGenerable, winningNumberRepository);
    }

    NumberGeneratorFacade buildModuleForTests(WinningNumberGenerable winningNumberGenerable,
                                              WinningNumberRepository winningNumberRepository) {
        return buildModuleForClient(winningNumberGenerable, winningNumberRepository);
    }
}