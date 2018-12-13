import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class day11_part1 {
  public static void main(String[] args) {
    Map<Point,Long> board = new HashMap<>();
    int serial = 5791;
    for(int x=1;x<=300;x++)
    {
      for(int y=1;y<=300;y++)
      {
        Point p = new Point(x,y);
        int rackId = x+10;
        long powerLevel = rackId*y;
        powerLevel = powerLevel + serial;
        powerLevel = powerLevel * rackId;
        powerLevel = ((powerLevel / 10 ) / 10) %10;
        powerLevel = powerLevel -5;

        board.put(p,powerLevel);
      }
    }

    //Map center to total
    long maxScore = Long.MIN_VALUE;
    Map<Point,Long> scores = new HashMap<>();

    for(int x=1;x<=298;x++){
      for(int y=1;y<=298;y++){
        long score = 0;
        for(int z =x; z<=x+2;z++)
        {
          for(int t=y;t<=y+2;t++)
          {
            score = score + board.get(new Point(z,t));
          }
        }

        scores.put(new Point(x,y),score);
        System.out.print(score + " ");
      }
      System.out.println();
    }

    Point selected = null;
    for(Point p : scores.keySet())
    {
      if(scores.get(p) > maxScore)
      {
        selected = p;
        maxScore = scores.get(p);
      }
    }

    System.out.println((selected.getX())+ "," + (selected.getY()));
  }

  /*


   */
}
