package pl.lotto.resultannouncer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.resultchecker.WinnerTickets;
import pl.lotto.resultchecker.WinnersTicketDataBase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class WinnersTicketDataBaseTestImpl implements WinnersTicketDataBase {

    List<WinnerTickets> winnerTickets;

    public WinnersTicketDataBaseTestImpl(List<WinnerTickets> winnerTickets) {
        this.winnerTickets = winnerTickets;
    }

    @Override
    public List<WinnerTickets> save(List<WinnerTickets> ticketDto) {
        winnerTickets.addAll(ticketDto);
        return ticketDto;
    }

    @Override
    public Optional<WinnerTickets> findByHashAndDateTimeNextDraw(String id, LocalDateTime dateToGetWinnersTicket) {
        return Optional.empty();
    }

    @Override
    public <S extends WinnerTickets> S save(S entity) {
        return null;
    }

    @Override
    public <S extends WinnerTickets> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WinnerTickets> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<WinnerTickets> findAll() {
        return null;
    }

    @Override
    public Iterable<WinnerTickets> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(WinnerTickets entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends WinnerTickets> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<WinnerTickets> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<WinnerTickets> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinnerTickets> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends WinnerTickets> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends WinnerTickets> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WinnerTickets> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends WinnerTickets> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends WinnerTickets> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinnerTickets> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WinnerTickets> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends WinnerTickets, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}

