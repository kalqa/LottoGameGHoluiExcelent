package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lotto.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WinnersTicketDataBase extends MongoRepository<List<TicketDto>, LocalDateTime> {

    TicketDto save(TicketDto ticketDtos);
//    List<TicketDto> save(List<TicketDto> ticketDtos);

//    <S extends T> S save(S entity);

//    List<TicketDto> save(List<TicketDto> winnerNumbers);


//    @Override
//    public WinnerNumbers save(WinnerNumbers winnerNumbers) {
//        return winnerNumbers;
//    }


    boolean checkIfUserWon(String id);


}
