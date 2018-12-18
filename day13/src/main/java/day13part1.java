
import com.google.common.collect.ImmutableList;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day13part1 {
  public static void main(String[] args) throws IOException {
    day13part1 part1 = new day13part1();
    part1.run();
  }
  private void run() throws IOException {

    File input = new File("/home/awlerma/git/adventofcode18/day13/src/main/resources/input.txt");
    BufferedReader reader = new BufferedReader(new FileReader(input));
    String line;
    Map<Point,Track> board = new HashMap<>();
    int x;
    int y=0;
    int maxX=0;
    int maxY=0;

    List<Point> pointsWithCarts = new ArrayList<>();
    while ((line = reader.readLine()) != null) {
      char[] chars = line.toCharArray();
      for(x=0;x<chars.length;x++)
      {
        Point p = new Point(x,y);
        Track track = parseTrack(chars[x]);
        if(track.getCart().cartType != Track.CartType.NONE)
        {
          pointsWithCarts.add(p);
        }
        board.put(p,track);
        maxX = Math.max(x,maxX);
      }
      y++;
      maxY= Math.max(y,maxY);
    }

    printBoard(board, maxX, maxY);
    whileLoop:
    while(true)
    {
      List<Point> newPointsWithCarts = new ArrayList<>();

      List<Point> copyOfPwc = ImmutableList.copyOf(pointsWithCarts);
      for(int i=0;i<copyOfPwc.size();i++)
      {
        Point p = copyOfPwc.get(i);
        if(applyMove(p,board,pointsWithCarts,newPointsWithCarts))
        {
          break whileLoop;
        }
        else {
          //System.out.println(p.getX() + "," + p.getY());
        }
      }
      pointsWithCarts = newPointsWithCarts;

      printBoard(board, maxX, maxY);
    }

    printBoard(board, maxX, maxY);
  }

  private boolean applyMove(Point p, Map<Point,Track> board, List<Point> pointsWithCarts, List<Point> newPointsWithCarts)
  {
    Track currentTrack = board.get(p);
    if(currentTrack.getCart().cartType == Track.CartType.NONE)
    {
      return false;
    }
    Point nextPoint = getNextPoint(p, currentTrack.getCart().cartType);
    Track nextTrack = board.get(nextPoint);
    if(nextTrack.getCart().cartType != Track.CartType.NONE)
    {
      System.out.println(nextPoint);
      return  true;
    }

    pointsWithCarts.remove(p);
    newPointsWithCarts.add(nextPoint);

    //next location doesnt have a cart.

    //remove cart from current location
    Cart currentCart = currentTrack.getCart();
    board.get(p).setCart(new Cart(Track.CartType.NONE));
    //Move cart to next location
    nextTrack.setCart(currentCart);
    // then change its type according to rules
    switch (nextTrack.getTrack())
    {
      case NONE:
        throw new IllegalStateException();
      case VERTICAL:
      case HORIZONTAL:
        break;
      case LEFT_CORNER:
        switch (currentCart.cartType)
        {
          case UP:
            currentCart.cartType = Track.CartType.RIGHT;
            break;
          case DOWN:
            currentCart.cartType = Track.CartType.LEFT;
            break;
          case LEFT:
            currentCart.cartType = Track.CartType.DOWN;
            break;
          case RIGHT:
            currentCart.cartType = Track.CartType.UP;
            break;
        }
        break;
      case RIGHT_CORNER:
        switch (currentCart.cartType)
        {
          case UP:
            currentCart.cartType = Track.CartType.LEFT;
            break;
          case DOWN:
            currentCart.cartType = Track.CartType.RIGHT;
            break;
          case LEFT:
            currentCart.cartType = Track.CartType.UP;
            break;
          case RIGHT:
            currentCart.cartType = Track.CartType.DOWN;
            break;

        }
        break;
      case INTERSECTION:

        switch (currentCart.numTurns)
        {
          case 0:
            currentCart.numTurns = 1;
            //LEFT
            switch (currentCart.cartType)
            {
              case UP:
                currentCart.cartType = Track.CartType.LEFT;
                break;
              case DOWN:
                currentCart.cartType = Track.CartType.RIGHT;
                break;
              case LEFT:
                currentCart.cartType = Track.CartType.DOWN;
                break;
              case RIGHT:
                currentCart.cartType = Track.CartType.UP;
                break;
            }
            break;
          case 1:
            currentCart.numTurns = 2;
            //STRAIGHT
            //No change in cart type
            break;
          case 2:
            //RIGHT
            currentCart.numTurns = 0;
            switch (currentCart.cartType)
            {
              case UP:
                currentCart.cartType = Track.CartType.RIGHT;
                break;
              case DOWN:
                currentCart.cartType = Track.CartType.LEFT;
                break;
              case LEFT:
                currentCart.cartType = Track.CartType.UP;
                break;
              case RIGHT:
                currentCart.cartType = Track.CartType.DOWN;
                break;
            }
            break;

        }

        break;

    }

    return false;
  }

  private Point getNextPoint(Point p, Track.CartType cartType) {
    Point nextPoint = new Point(0,0);
    if(cartType == Track.CartType.LEFT)
    {
      nextPoint =   new Point(p.x-1,p.y);
    }
    if(cartType == Track.CartType.RIGHT)
    {
      nextPoint =   new Point(p.x+1,p.y);
    }
    if(cartType == Track.CartType.UP)
    {
      nextPoint =   new Point(p.x,p.y-1);
    }
    if(cartType == Track.CartType.DOWN)
    {
      nextPoint =   new Point(p.x,p.y+1);
    }

    return nextPoint;
  }

  private void printBoard(Map<Point,Track> board, int maxX, int maxY) {
    for(int i = 0; i<=maxY; i++)
    {
      for(int j=0;j<=maxX;j++) {
        Point p = new Point(j,i);
        if(board.containsKey(p))
        {
          System.out.print(board.get(p));
        }
      }
      System.out.println();
    }
  }

  private Track parseTrack(char aChar) {
    Track.TrackType type = Track.TrackType.NONE;
    Cart cart = new Cart(Track.CartType.NONE);
    switch (aChar){
      case ' ':  type = Track.TrackType.NONE;
        break;
      case '/':  type = Track.TrackType.LEFT_CORNER;
        break;
      case '\\': type = Track.TrackType.RIGHT_CORNER;
        break;
      case '-':  type = Track.TrackType.HORIZONTAL;
        break;
      case '|':  type = Track.TrackType.VERTICAL;
        break;
      case '+':  type = Track.TrackType.INTERSECTION;
        break;
      default: //it must be a cart
        switch (aChar){
          case '<':
            cart = new Cart(Track.CartType.LEFT);
            type = Track.TrackType.HORIZONTAL;
            break;
          case '>':
            cart = new Cart(Track.CartType.RIGHT);
            type = Track.TrackType.HORIZONTAL;
            break;
          case '^':
            cart = new Cart(Track.CartType.UP);
            type = Track.TrackType.VERTICAL;
            break;
          case 'v':
            cart = new Cart(Track.CartType.DOWN);
            type = Track.TrackType.VERTICAL;
            break;

        }
    }

    return  new Track(type,cart);
  }
}
