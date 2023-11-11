package com.odeyalo.sonata.miku.service.event.source;

import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/**
 * Implementation of EmittingEventSource that uses Sinks.Many<T> to push and receive events.
 *
 * @param <T> - type of the event
 */
@Component
public class SinkEmittingEventSource<T extends SonataEvent> implements EmittingEventSource<T>, AutoCloseable {
    private final Sinks.Many<T> publisher = Sinks.many().multicast().onBackpressureBuffer();

    @Override
    public Mono<Void> emitNext(T event) {
        publisher.tryEmitNext(event);
        return Mono.empty();
    }

    @Override
    public Flux<T> getEvents() {
        return publisher.asFlux();
    }

    @Override
    public void close() {
        publisher.tryEmitComplete();
    }
}