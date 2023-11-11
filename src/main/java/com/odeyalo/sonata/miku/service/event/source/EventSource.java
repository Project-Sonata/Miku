package com.odeyalo.sonata.miku.service.event.source;

import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import reactor.core.publisher.Flux;

/**
 * Event source is used to provided single-type event Flux for event listeners.
 *
 * @param <T> - type of the event to listen to
 * @see EmittingEventSource - to publish the events to EventSource
 */
public interface EventSource<T extends SonataEvent> {
    /**
     * @return Infinity hot-publisher Flux with events
     */
    Flux<T> getEvents();

}
