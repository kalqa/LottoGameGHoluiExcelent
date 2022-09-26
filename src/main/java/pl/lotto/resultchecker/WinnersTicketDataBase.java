package pl.lotto.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WinnersTicketDataBase extends MongoRepository<WinnerTickets, Long> {

//    void addWinnerTicketsToDataBase(List<WinnerTickets> ticketDto);

    List<WinnerTickets> save(List<WinnerTickets> ticketDto);

    Optional<WinnerTickets> findByHashAndByDate(String id);


}
