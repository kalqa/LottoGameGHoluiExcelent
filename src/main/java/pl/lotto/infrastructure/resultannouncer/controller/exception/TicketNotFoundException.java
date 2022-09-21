package pl.lotto.infrastructure.resultannouncer.controller.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String id) {
        super("Could not find Ticket: " + id);
    }
}
