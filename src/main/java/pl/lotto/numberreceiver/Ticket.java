package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@Document(collection = "tickets")

public class Ticket {

    @Id
    String id;

    String hash;
    List<Integer> userNumbers;
    LocalDateTime nextDrawDate;

    Ticket(String id, String hash, List<Integer> userNumbers, LocalDateTime nextDrawDate) {
        this.id = id;
        this.hash = hash;
        this.userNumbers = userNumbers;
        this.nextDrawDate = nextDrawDate;
    }

    @PersistenceConstructor
    public Ticket(String hash, List<Integer> userNumbers, LocalDateTime nextDrawDate) {
        this.hash = hash;
        this.userNumbers = userNumbers;
        this.nextDrawDate = nextDrawDate;
    }
}
