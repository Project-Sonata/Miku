package com.odeyalo.sonata.miku.service.event;

import com.odeyalo.sonata.miku.service.event.source.SinkEmittingEventSource;
import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import testing.event.MockSonataEvent;
import testing.event.SpyEventListener;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListenerInvocationCapableEventManagerTest {

    @Test
    void shouldInvokeEventListeners() throws InterruptedException {
        // Given
        SinkEmittingEventSource<SonataEvent> eventSource = new SinkEmittingEventSource<>();
        SpyEventListener spyListener = new SpyEventListener();

        ListenerInvocationCapableEventManager testable = new ListenerInvocationCapableEventManager(
                Collections.singletonList(spyListener), eventSource
        );
        // Initiate listening to events and delegating
        testable.startEventListening();

        var event = new MockSonataEvent("I love Miku!");
        // When
        eventSource.emitNext(event)
                .as(StepVerifier::create)
                .verifyComplete();
        // wait half of second to ensure that listeners have been called
        Thread.sleep(500);
        // Then
        assertThat(spyListener.hasBeenCalled()).isTrue();
    }

    @Test
    void shouldAddListenerToRegistry() {
        // given
        SpyEventListener listener1 = new SpyEventListener();

        ListenerInvocationCapableEventManager testable = new ListenerInvocationCapableEventManager(
                Collections.singletonList(listener1), new SinkEmittingEventSource<>()
        );
        SpyEventListener listener2 = new SpyEventListener();
        // when
        testable.addListener(listener2);

        // then
        assertThat(testable.getListeners()).containsAll(List.of(listener1, listener2));
    }

    @Test
    void shouldRemoveListenerFromRegistry() {
        // given
        SpyEventListener listener1 = new SpyEventListener();

        ListenerInvocationCapableEventManager testable = new ListenerInvocationCapableEventManager(
                Collections.singletonList(listener1), new SinkEmittingEventSource<>()
        );

        // when
        testable.deleteListener(listener1);

        // then
        assertThat(testable.getListeners()).isEmpty();
    }

    @Test
    void shouldInvokeNewlyAddedEventListenersToo() {
        // Given
        SinkEmittingEventSource<SonataEvent> eventSource = new SinkEmittingEventSource<>();
        SpyEventListener spyListener1 = new SpyEventListener();

        ListenerInvocationCapableEventManager testable = new ListenerInvocationCapableEventManager(
                Collections.singletonList(spyListener1), eventSource
        );
        // Initiate listening to events and delegating
        testable.startEventListening();

        SpyEventListener spyListener2 = new SpyEventListener();
        testable.addListener(spyListener2);

        var event = new MockSonataEvent("I love Miku!");
        // When
        eventSource.emitNext(event)
                .as(StepVerifier::create)
                .verifyComplete();

        // Then
        assertThat(spyListener1.hasBeenCalled()).isTrue();
        assertThat(spyListener2.hasBeenCalled()).isTrue();
    }

    @Test
    void shouldNotInvokeDeletedEventListener() {
        // Given
        SinkEmittingEventSource<SonataEvent> eventSource = new SinkEmittingEventSource<>();
        SpyEventListener spyListener1 = new SpyEventListener();
        SpyEventListener spyListener2 = new SpyEventListener();

        ListenerInvocationCapableEventManager testable = new ListenerInvocationCapableEventManager(
                List.of(spyListener1, spyListener2), eventSource
        );
        // Initiate listening to events and delegating
        testable.startEventListening();

        testable.deleteListener(spyListener2);

        var event = new MockSonataEvent("I love Miku!");
        // When
        eventSource.emitNext(event)
                .as(StepVerifier::create)
                .verifyComplete();

        // Then
        assertThat(spyListener1.hasBeenCalled()).isTrue();
        assertThat(spyListener2.hasBeenCalled()).isFalse();
    }
}