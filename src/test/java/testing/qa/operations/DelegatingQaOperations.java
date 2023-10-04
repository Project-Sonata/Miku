package testing.qa.operations;

/**
 * QaOperations impl that just delegate to other impls
 */
public class DelegatingQaOperations implements QaOperations {
    private final TrackOperations trackOperations;
    private final AlbumOperations albumOperations;

    public DelegatingQaOperations(TrackOperations trackOperations, AlbumOperations albumOperations) {
        this.trackOperations = trackOperations;
        this.albumOperations = albumOperations;
    }

    @Override
    public TrackOperations tracks() {
        return trackOperations;
    }

    @Override
    public AlbumOperations albums() {
        return albumOperations;
    }
}
