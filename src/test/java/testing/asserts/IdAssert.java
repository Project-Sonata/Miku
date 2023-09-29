package testing.asserts;

import org.assertj.core.api.AbstractStringAssert;

public class IdAssert extends AbstractStringAssert<IdAssert> {

    public IdAssert(String actual) {
        super(actual, IdAssert.class);
    }
}