package testing.event;

import com.odeyalo.sonata.miku.service.event.EventListener;
import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpyEventListener implements EventListener {
    private final AtomicBoolean hasCalled = new AtomicBoolean();

    @Override
    public Mono<Void> handleEvent(SonataEvent sonataEvent) {
        hasCalled.setRelease(true);
        return Mono.empty();
    }

    @Override
    public boolean supports(SonataEvent event) {
        return true;
    }

    public boolean hasBeenCalled() {
        return hasCalled.get();
    }
}
