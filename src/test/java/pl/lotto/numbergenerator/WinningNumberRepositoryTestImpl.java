package pl.lotto.numbergenerator;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

class WinningNumberRepositoryTestImpl implements WinningNumberRepository {
    Map<LocalDateTime, List<Integer>> winningNumbers;

    public WinningNumberRepositoryTestImpl(Map<LocalDateTime, List<Integer>> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    @Override
    public WinnerNumbers save(WinnerNumbers winnerNumbers) {
        winningNumbers.put(winnerNumbers.getDateOfWinnerNumbers(), winnerNumbers.getNumbers());
        return winnerNumbers;
    }

    @Override
    public WinnerNumbers findByDateOfWinnerNumbers(LocalDateTime dateToGet) {
        return null;
    }

//    @Override
//    public List<Integer> findByDateOfWinnerNumbers(LocalDateTime dateToGet) {
//        return winningNumbers.get(dateToGet);
//    }

    @Override
    public List<Integer> getWinnerNumbersByDateOfWinnerNumbers(LocalDateTime dateToGet) {
        return null;
    }


    @Override
    public <S extends WinnerNumbers> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<WinnerNumbers> findById(LocalDateTime dateTime) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(LocalDateTime dateTime) {
        return false;
    }

    @Override
    public List<WinnerNumbers> findAll() {
        return null;
    }

    @Override
    public Iterable<WinnerNumbers> findAllById(Iterable<LocalDateTime> localDateTimes) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(LocalDateTime dateTime) {

    }

    @Override
    public void delete(WinnerNumbers entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends LocalDateTime> localDateTimes) {

    }

    @Override
    public void deleteAll(Iterable<? extends WinnerNumbers> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<WinnerNumbers> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<WinnerNumbers> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinnerNumbers> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends WinnerNumbers> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends WinnerNumbers> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends WinnerNumbers> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends WinnerNumbers> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends WinnerNumbers> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends WinnerNumbers> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends WinnerNumbers> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends WinnerNumbers, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
