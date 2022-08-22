package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class TicketRepositoryTestImpl implements TicketRepository {
    Map<String, Ticket> tickets;

    public TicketRepositoryTestImpl(Map<String, Ticket> tickets) {
        this.tickets = tickets;
    }

    public TicketRepositoryTestImpl() {
    }

    //    @Override
    public void save(Ticket ticket) {
        tickets.put(ticket.hash, ticket);
    }

    @Override
    public Map<String, Ticket> getAllTickets() {
        return tickets;
    }

    @Override
    public List<Ticket> findAllByNextDrawDate(LocalDateTime nextDrawDate) {
        return tickets.entrySet()
                .stream()
                .filter(currentElement -> currentElement.getValue().nextDrawDate.equals(nextDrawDate))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Ticket> getTicketsForGivenDate(LocalDateTime drawDate) {
        return findAllByNextDrawDate(drawDate);

    }


    public Optional<Ticket> findTicketByHash(String currentTicketHash) {
        return tickets
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(currentTicket -> currentTicket.getHash().equals(currentTicketHash))
                .findFirst();
    }


//    @Override
//    public <S extends Ticket> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<Ticket> findById(String s) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(String s) {
//        return false;
//    }
//
//    @Override
//    public List<Ticket> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<Ticket> findAllById(Iterable<String> strings) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(String s) {
//
//    }
//
//    @Override
//    public void delete(Ticket entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Ticket> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public List<Ticket> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Ticket> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Ticket> S insert(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Ticket> List<S> insert(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public <S extends Ticket> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends Ticket> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends Ticket> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends Ticket> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Ticket> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends Ticket> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends Ticket, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
}
