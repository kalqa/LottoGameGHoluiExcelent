package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository {
    // here was that TicketRepository extends MongoRepository<Ticket, String>

    void save(Ticket ticket);

    Map<String, Ticket> getAllTickets();

    List<Ticket> findAllByNextDrawDate(LocalDateTime nextDrawDate);

    List<Ticket> getTicketsForGivenDate(LocalDateTime dateToGet);

    Optional<Ticket> findTicketByHash(String currentTicketHash);

//    void saveForTests(Map<LocalDateTime, List<Ticket>> tickets);

}
