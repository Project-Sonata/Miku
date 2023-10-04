package testing.qa.operations;

/**
 * QaOperations impl that just delegate to other impls
 */
public class DelegatingQaOperations implements QaOperations {
    private final TrackOperations trackOperations;

    public DelegatingQaOperations(TrackOperations trackOperations) {
        this.trackOperations = trackOperations;
    }

    @Override
    public TrackOperations tracks() {
        return trackOperations;
    }
}
