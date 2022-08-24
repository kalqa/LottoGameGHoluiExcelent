package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "tickets")
class Ticket {
    String hash;
    List<Integer> userNumbers;
    LocalDateTime nextDrawDate;
}
