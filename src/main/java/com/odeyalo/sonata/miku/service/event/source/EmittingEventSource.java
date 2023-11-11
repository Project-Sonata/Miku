package com.odeyalo.sonata.miku.service.event.source;

import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import reactor.core.publisher.Mono;

/**
 * Extension for EventSource that add ability to emit the element to EventSource.
 *
 * @param <T> - type of Event to publish and receive
 */
public interface EmittingEventSource<T extends SonataEvent> extends EventSource<T> {

    Mono<Void> emitNext(T event);

}
