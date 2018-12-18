import org.omg.PortableInterceptor.DISCARDING;

import java.awt.*;
import java.util.Map;
import java.util.Set;

public class Cart implements  Comparable{
    public int compareTo(Object o) {
        if(o instanceof Cart){
            int x = position.x;
            int y = position.y;
            int otherX = ((Cart)o).getPosition().x;
            int otherY  = ((Cart)o).getPosition().y;
            if(x == otherX && y == otherY)
            {
                return 0;
            }
            if(y == otherY)
            {
                return Integer.compare(x,otherX);
            }
            return Integer.compare(y,otherY);

        }
        else throw new IllegalArgumentException("Uh, howd you get here");

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

    public boolean detectCrash(Map<Point, Track> board){
        //update position
        //update direction
        Point nextPoint = getNextPoint();

        Track trackAtNewPosition = board.get(nextPoint);

        if(trackAtNewPosition.getCart()!= null)
        {
            return true;
        }
        return false;

    }

    public Direction getNextDirection(Track trackAtNewPosition) {
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
                        numTurns=0;
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

        return direction;
    }

    public Point getNextPoint() {
        Point nextPoint = new Point();
        switch (this.direction)
        {
            case UP:
                nextPoint = new Point(position.x, position.y-1);
                break;
            case DOWN:
                nextPoint = new Point(position.x, position.y+1);
                break;
            case LEFT:
                nextPoint = new Point(position.x-1, position.y);
                break;
            case RIGHT:
                nextPoint = new Point(position.x+1, position.y);
                break;
        }
        return nextPoint;
    }
}
