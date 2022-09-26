package pl.lotto.feature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.infrastructure.numbergenerator.db.scheduler.GeneratorNumbers;
import pl.lotto.numbergenerator.WinningNumberGenerable;

import java.util.List;

@Configuration
public class IntegrationConfiguration {
    @Bean
    WinningNumberGenerable winningNumberGenerable() {
        return () -> List.of(1, 2, 3, 4, 5, 6);
    }

    @Bean
    GeneratorNumbers generatorNumbers() {
        return new NumberGeneratorSchedulerTest();
    }

}
