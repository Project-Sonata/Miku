package testing.event;

import com.odeyalo.sonata.miku.service.event.EventListener;
import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpyEventListener implements EventListener {
    private final AtomicBoolean hasCalled = new AtomicBoolean();

    @Override
    @NotNull
    public Mono<Void> handleEvent(@NotNull final SonataEvent sonataEvent) {
        hasCalled.setRelease(true);
        return Mono.empty();
    }

    @Override
    public boolean supports(@NotNull final SonataEvent event) {
        return true;
    }

    public boolean hasBeenCalled() {
        return hasCalled.get();
    }
}
