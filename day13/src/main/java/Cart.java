import java.util.UUID;

public class Cart {
  public Track.CartType cartType = Track.CartType.NONE;
  public int numTurns = 0;

  public Cart(Track.CartType cartType) {
    this.cartType = cartType;
  }

  public UUID getId() {
    return id;
  }

  private UUID id = UUID.randomUUID();
  @Override public String toString() {
    switch (cartType)
    {
      case UP:
        return "^";
      case DOWN:
        return "v";
      case LEFT:
        return "<";
      case RIGHT:
        return ">";
    }
    return "";
  }
}
