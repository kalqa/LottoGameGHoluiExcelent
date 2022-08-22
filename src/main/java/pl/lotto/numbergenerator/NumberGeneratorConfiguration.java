package pl.lotto.numbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberGeneratorConfiguration {

    @Bean
    NumberGeneratorFacade buildModuleForClient(WinningNumberGenerable winningNumberGenerable,
                                               WinningNumberRepository winningNumberRepository) {
        return new NumberGeneratorFacade(winningNumberGenerable, winningNumberRepository);
    }

    NumberGeneratorFacade buildModuleForTests(WinningNumberGenerable winningNumberGenerable,
                                              WinningNumberRepository winningNumberRepository) {
        return buildModuleForClient(winningNumberGenerable, winningNumberRepository);
    }
}
