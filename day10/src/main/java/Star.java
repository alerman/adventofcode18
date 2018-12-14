import java.awt.*;

public class Star {
  Point p;
  int xVelocity;
  int yVelocity;

  public Star(Point p, int xVelocity, int yVelocity) {
    this.p = p;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
  }

  public Point getP() {
    return p;
  }

  public void setP(Point p) {
    this.p = p;
  }

  public int getxVelocity() {
    return xVelocity;
  }

  public void setxVelocity(int xVelocity) {
    this.xVelocity = xVelocity;
  }

  public int getyVelocity() {
    return yVelocity;
  }

  public void setyVelocity(int yVelocity) {
    this.yVelocity = yVelocity;
  }

  public void applyMove()
  {
    this.p = new Point(p.x+ xVelocity, p.y+yVelocity);
  }



  public void unapplyMove()
  {
    this.p = new Point(p.x - xVelocity, p.y - yVelocity);
  }
}
