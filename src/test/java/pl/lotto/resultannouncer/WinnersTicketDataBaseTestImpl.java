package pl.lotto.resultannouncer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.WinnersTicketDataBase;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class WinnersTicketDataBaseTestImpl implements WinnersTicketDataBase {

    List<TicketDto> winnerTickets;

    public WinnersTicketDataBaseTestImpl(List<TicketDto> winnerTickets) {
        this.winnerTickets = winnerTickets;
    }

    @Override
    public List<TicketDto> save(List<TicketDto> ticketDtos) {
        winnerTickets.addAll(ticketDtos);
        return ticketDtos;
    }




}