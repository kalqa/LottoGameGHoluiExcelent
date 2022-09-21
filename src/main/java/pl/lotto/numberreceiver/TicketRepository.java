package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

//    Ticket save(Ticket ticket);

//    Map<String, Ticket> getAllTickets();

    List<Ticket> findTicketsByNextDrawDate(LocalDateTime nextDrawDate);

//    List<Ticket> findAllByNextDrawDateAndHashOrderByNextDrawDateDesc(LocalDateTime nextDrawDate, String hash);
//
//    List<Ticket> getTicketsForGivenDate(LocalDateTime dateToGet);

    Optional<Ticket> findTicketByHash(String currentTicketHash);

//    void saveForTests(Map<LocalDateTime, List<Ticket>> tickets);

}
