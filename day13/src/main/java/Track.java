public class Track {
  protected enum TrackType{
    LEFT_CORNER,
    RIGHT_CORNER,
    HORIZONTAL,
    VERTICAL,
    INTERSECTION,
    NONE
  }

  protected enum CartType{
    LEFT,
    RIGHT,
    UP,
    DOWN,
    NONE
  }

  private TrackType track = TrackType.NONE;
  private Cart cart = new Cart(CartType.NONE);

  public Track(TrackType track, Cart cart)
  {
    this.track = track;
    this.cart = cart;
  }

  public TrackType getTrack() {
    return track;
  }

  public void setTrack(TrackType track) {
    this.track = track;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  @Override public String toString() {
    if(cart.cartType == CartType.NONE)
    {
      switch (track)
      {
        case LEFT_CORNER:
          return "/";
        case RIGHT_CORNER:
          return "\\";
        case HORIZONTAL:
          return "-";
        case VERTICAL:
          return "|";
        case INTERSECTION:
          return "+";
        case NONE:
          return " ";
      }
    } else{
      return cart.toString();
    }

    throw new IllegalStateException("Should have had a cart, or a track");
  }
}
