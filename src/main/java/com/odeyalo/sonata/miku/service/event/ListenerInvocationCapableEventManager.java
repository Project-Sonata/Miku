package com.odeyalo.sonata.miku.service.event;

import com.odeyalo.sonata.miku.service.event.source.EventSource;
import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Capable to auto-invoke registered listeners.
 */
@Component
public class ListenerInvocationCapableEventManager implements EventListenerRegistry {
    private final Collection<EventListener> listeners;
    private final EventSource<SonataEvent> eventSource;
    private Flux<EventListener> cachedListeners;

    public ListenerInvocationCapableEventManager(Collection<EventListener> listeners, EventSource<SonataEvent> eventSource) {
        this.listeners = new ArrayList<>(listeners);
        this.eventSource = eventSource;
        this.cachedListeners = Flux.fromIterable(listeners);
    }

    @PostConstruct
    public void startEventListening() {
        eventSource.getEvents()
                .flatMap(event -> cachedListeners
                        .filter(listener -> listener.supports(event))
                        .flatMap(listener -> listener.handleEvent(event)))
                .subscribeOn(Schedulers.parallel())
                .subscribe();
    }

    @Override
    public void addListener(@NotNull EventListener eventListener) {
        listeners.add(eventListener);
        refreshCachedListeners();
    }

    @Override
    public void deleteListener(@NotNull EventListener eventListener) {
        listeners.remove(eventListener);
        refreshCachedListeners();
    }

    @Override
    @NotNull
    public Collection<EventListener> getListeners() {
        return listeners;
    }

    private void refreshCachedListeners() {
        this.cachedListeners = Flux.fromIterable(listeners);
    }
}
