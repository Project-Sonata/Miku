package com.odeyalo.sonata.miku.service.event;

import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

/**
 * Listen to SonataEvent and can handle it
 */
public interface EventListener {
    /**
     * Handle the given event.
     *
     * @param sonataEvent - event to handle or ignore
     * @return - empty mono after execution.
     * Any exception will be ignored and must be handled in listener
     */
    @NotNull
    Mono<Void> handleEvent(@NotNull SonataEvent sonataEvent);

    /**
     * Filter function that used to filter incoming events
     *
     * @param event - event to filter
     * @return - true if this event listener can handle this event, false otherwise
     */
    boolean supports(@NotNull SonataEvent event);
}
