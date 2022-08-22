package pl.lotto.infrastructure.numbergenerator.db;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface WinningNumberMongoRepository extends MongoRepository<WinningNumberDocument, String> {

}
