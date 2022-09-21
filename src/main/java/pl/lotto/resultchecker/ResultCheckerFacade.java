package pl.lotto.resultchecker;

import lombok.AllArgsConstructor;
import pl.lotto.numberreceiver.dto.TicketDto;
import pl.lotto.resultchecker.dto.ResultCheckerDto;
import pl.lotto.infrastructure.resultannouncer.controller.exception.TicketNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ResultCheckerFacade {

    WinnerTicketCheckable winnerTicketCheckable;
    WinnerDataLoader winnerDataLoader;
    WinnersTicketDataBase winnersTicketDataBase;

    public WinnersTicketDataBase getWinnersTicketDataBase() {
        return winnersTicketDataBase;
    }

    public ResultCheckerDto winners(LocalDateTime dateToGetWinnersTicket) {
        // this method return all winning TicketsDTO
        List<TicketDto> winnersTicket = getWinnersTicket(dateToGetWinnersTicket);
        return new ResultCheckerDto(winnersTicket);
    }

    public boolean winner(String userId) {
        WinnersTicketDataBase winnersTicketDataBase = getWinnersTicketDataBase();
        Optional<WinnerTickets> byHash = winnersTicketDataBase.findByHash(userId);
        boolean empty = byHash.isPresent();
        return empty;
    }

    private List<TicketDto> getWinnersTicket(LocalDateTime dateToGetWinnersTicket) {
        List<Integer> winnerNumbers = winnerDataLoader.getWinnerNumbers(dateToGetWinnersTicket);
        List<TicketDto> tickets = winnerDataLoader.getTickets(dateToGetWinnersTicket);
        List<TicketDto> ticketDtos = winnerTicketCheckable.checkWhichTicketWon(tickets, winnerNumbers);
        List<WinnerTickets> winnerTicketsList = mapTicketsDtoToWinnerNumbers(ticketDtos);
        winnersTicketDataBase.saveAll(winnerTicketsList);
        return ticketDtos;
    }

    private List<WinnerTickets> mapTicketsDtoToWinnerNumbers(List<TicketDto> ticketDtos) {
        return ticketDtos.stream()
                .map(ticketDto ->
                        new WinnerTickets(ticketDto.hash(), ticketDto.userNumbers(), ticketDto.dateAndTimeNextDraw()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
