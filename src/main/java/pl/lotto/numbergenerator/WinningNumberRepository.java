package pl.lotto.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface WinningNumberRepository extends MongoRepository<WinnerNumbers, LocalDateTime> {

//    void save(List<Integer> numbers, LocalDateTime currentDate);

    WinnerNumbers save(WinnerNumbers winnerNumbers);


//    <S extends T> S save(S entity);


    List<Integer> getWinnerNumbersByDateOfWinnerNumbers(LocalDateTime dateToGet);


}
