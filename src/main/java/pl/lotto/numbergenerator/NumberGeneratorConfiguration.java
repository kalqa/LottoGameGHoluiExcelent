package pl.lotto.numbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class NumberGeneratorConfiguration {

    @Bean
    NumberGeneratorFacade numberGeneratorFacade(WinningNumberGenerable winningNumberGenerable,
                                                WinningNumberRepository winningNumberRepository) {
        return new NumberGeneratorFacade(winningNumberGenerable, winningNumberRepository);
    }

    NumberGeneratorFacade numberGeneratorFacadeTest(WinningNumberGenerable winningNumberGenerable,
                                                    WinningNumberRepository winningNumberRepository) {
        return numberGeneratorFacade(winningNumberGenerable, winningNumberRepository);
    }


}