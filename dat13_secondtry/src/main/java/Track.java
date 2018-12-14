import java.awt.*;

public class Track {
    public boolean hasCart() {
        return hasCart;
    }

    enum TrackType{
        LEFT_CORNER,
        RIGHT_CORNER,
        INTERSECTION,
        HORIZONTAL,
        VERTICAL
    }

    private TrackType trackType;
    private boolean hasCart = false;
    private Point position;

    public Track(Point p, TrackType trackType) {
        this.trackType = trackType;
        this.position = p;
    }

    public void setHasCart(boolean hasCart) {
        this.hasCart = hasCart;
    }

    public TrackType getTrackType() {
        return trackType;
    }

    public void setTrackType(TrackType trackType) {
        this.trackType = trackType;
    }
}
