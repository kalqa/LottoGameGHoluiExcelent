package pl.lotto.numbergenerator;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WinningNumberRepository extends MongoRepository<WinnerNumbers, UUID> {

//    void save(List<Integer> numbers, LocalDateTime currentDate);

//    WinnerNumbers save(WinnerNumbers winnerNumbers);



    Optional<WinnerNumbers> findFirstByDrawDate(LocalDateTime drawDate);
//    List<Integer> findByDateOfWinnerNumbers(LocalDateTime dateToGet);


    List<Integer> getWinnerNumbersByDrawDate(LocalDateTime drawDate);

    boolean existsByDrawDate(LocalDateTime currentDate);
}
