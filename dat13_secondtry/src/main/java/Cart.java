import org.omg.PortableInterceptor.DISCARDING;

import java.awt.*;
import java.util.Map;

public class Cart implements  Comparable{
    public int compareTo(Object o) {
        double thisOne = 0;
        double otherOne = 0;
        if(o instanceof Cart) {
            thisOne = Math.sqrt(position.x^2+position.y^2);
            otherOne = Math.sqrt(((Cart) o).getPosition().x^2+((Cart) o).getPosition().y^2);
        }
        else throw new IllegalArgumentException("Uh, howd you get here");

        return Double.compare(thisOne, otherOne);
    }

    enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    Point position;
    Direction direction;
    int numTurns = 0;

    public Cart(Point position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean applyMove(Map<Point, Track> board){
        //update position
        //update direction
        board.get(position).setHasCart(false);
        switch (this.direction)
        {
            case UP:
                this.position = new Point(position.x, position.y-1);
                break;
            case DOWN:
                this.position = new Point(position.x, position.y+1);
                break;
            case LEFT:
                this.position = new Point(position.x-1, position.y);
                break;
            case RIGHT:
                this.position = new Point(position.x+1, position.y);
                break;
        }

        Track trackAtNewPosition = board.get(position);

        if(trackAtNewPosition.hasCart())
        {
            return false;
        }

        trackAtNewPosition.setHasCart(true);
        switch (trackAtNewPosition.getTrackType())
        {
            // Case /
            case LEFT_CORNER:
                switch(direction){
                    case UP:
                        this.direction = Direction.RIGHT;
                        break;
                    case DOWN:
                        this.direction = Direction.LEFT;
                        break;
                    case LEFT:
                        this.direction = Direction.DOWN;
                        break;
                    case RIGHT:
                        this.direction = Direction.UP;
                        break;
                }
                break;
            // \
            case RIGHT_CORNER:
                switch(direction){
                    case UP:
                        this.direction = Direction.LEFT;
                        break;
                    case DOWN:
                        this.direction = Direction.RIGHT;
                        break;
                    case LEFT:
                        this.direction = Direction.UP;
                        break;
                    case RIGHT:
                        this.direction = Direction.DOWN;
                        break;
                }
                break;
            case INTERSECTION:
                switch(numTurns)
                {
                    // left
                    case 0:
                        switch(direction){
                            case UP:
                                this.direction = Direction.LEFT;
                                break;
                            case DOWN:
                                this.direction = Direction.RIGHT;
                                break;
                            case LEFT:
                                this.direction = Direction.UP;
                                break;
                            case RIGHT:
                                this.direction = Direction.DOWN;
                                break;
                        }
                        break;
                    case 1:
                        break;
                        // right
                    case 2:
                        switch(direction){
                            case UP:
                                this.direction = Direction.RIGHT;
                                break;
                            case DOWN:
                                this.direction = Direction.LEFT;
                                break;
                            case LEFT:
                                this.direction = Direction.DOWN;
                                break;
                            case RIGHT:
                                this.direction = Direction.UP;
                                break;
                        }
                        break;
                }
                numTurns++;
                break;
        }

        return  true;

    }
}
