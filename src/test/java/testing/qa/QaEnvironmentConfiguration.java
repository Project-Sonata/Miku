package testing.qa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.reactive.server.WebTestClient;
import testing.qa.operations.DelegatingQaOperations;
import testing.qa.operations.QaOperations;
import testing.qa.operations.TrackOperations;
import testing.qa.operations.WebTestClientTrackOperations;

/**
 * Configuration to bootstrap QA environment beans
 */
@Configuration
@Profile(value = {"test", "qa"})
public class QaEnvironmentConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TrackOperations trackOperations(WebTestClient webTestClient) {
        return new WebTestClientTrackOperations(webTestClient);
    }

    @Bean
    public QaOperations qaOperations(TrackOperations trackOperations) {
        return new DelegatingQaOperations(trackOperations);
    }
}
