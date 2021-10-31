package co.com.sofka.questions.reposioties;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.collections.Vote;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface VoteRepository extends ReactiveCrudRepository<Vote, String> {
    Flux<Vote> findByQuestionIdAndUserId(String questionId, String userId);

}
