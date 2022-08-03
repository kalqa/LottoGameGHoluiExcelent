package pl.lotto.numbergenerator;

public class NumberGeneratorConfiguration {
    NumberGeneratorFacade buildModuleForTests() {
        return new NumberGeneratorFacade();
    }
}
