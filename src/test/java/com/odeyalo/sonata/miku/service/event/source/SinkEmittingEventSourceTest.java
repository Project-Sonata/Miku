package com.odeyalo.sonata.miku.service.event.source;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import testing.event.MockSonataEvent;

class SinkEmittingEventSourceTest {
    SinkEmittingEventSource<MockSonataEvent> testable = new SinkEmittingEventSource<>();

    @Test
    void shouldPublishEventToFlux() {
        var sonataEvent = new MockSonataEvent("Miku I Love You!");
        Flux<MockSonataEvent> receivedEvents = testable.getEvents();

        testable.emitNext(sonataEvent)
                .as(StepVerifier::create)
                .verifyComplete();

        // Closing here to avoid infinity StepVerifier waiting
        testable.close();

        StepVerifier.create(receivedEvents)
                .expectNext(sonataEvent)
                .verifyComplete();
    }
}