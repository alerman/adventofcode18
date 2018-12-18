import java.awt.*;

public class Track {

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart(){
        return cart;
    }

    enum TrackType{
        LEFT_CORNER,
        RIGHT_CORNER,
        INTERSECTION,
        HORIZONTAL,
        VERTICAL
    }

    private TrackType trackType;
    private Cart cart = null;
    private Point position;

    public Track(Point p, TrackType trackType) {
        this.trackType = trackType;
        this.position = p;
    }

    public TrackType getTrackType() {
        return trackType;
    }

    public void setTrackType(TrackType trackType) {
        this.trackType = trackType;
    }
}
