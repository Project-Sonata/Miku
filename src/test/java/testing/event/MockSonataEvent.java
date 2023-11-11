package testing.event;

import com.odeyalo.sonata.suite.brokers.events.AbstractEvent;

public class MockSonataEvent extends AbstractEvent<String> {

    public MockSonataEvent(String body) {
        super(body);
    }
}
