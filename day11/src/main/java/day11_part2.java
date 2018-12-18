import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class day11_part2 {
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
    Map<Long, String> allResults = new HashMap<>();
    for(int i = 1; i<=300; i++) {
      Map<Point,Long> result = getMaxScore(board, i);
      Point answer = result.keySet().iterator().next();
      Long score = result.get(answer);
      String answerString = answer.x + "," + answer.y + "," + i;
      allResults.put(score, answerString);
    }
    Long max = Long.MIN_VALUE;
    for(Long score : allResults.keySet())
    {
      max = Math.max(score,max);
    }
    String answer = allResults.get(max);
    System.out.println(answer);
  }

  private static Map<Point,Long> getMaxScore(Map<Point,Long> board, int gridSize) {
    //Map center to total
    long maxScore = Long.MIN_VALUE;
    Map<Point,Long> scores = new HashMap<>();

    for(int x=1;x<=300-gridSize;x++){
      for(int y=1;y<=300-gridSize;y++){
        long score = 0;
        for(int z =x; z<=x+(gridSize-1);z++)
        {
          for(int t=y;t<=y+(gridSize-1);t++)
          {
            score = score + board.get(new Point(z,t));
          }
        }
        scores.put(new Point(x,y),score);
      }
    }
    Point selected = new Point();
    for(Point p : scores.keySet())
    {
      if(scores.get(p) > maxScore)
      {
        selected = p;
        maxScore = scores.get(p);
      }
    }
    Map<Point,Long> result = new HashMap();
    System.out.println("Max score for grid size " +  selected.getX() +","+ selected.getY() +","+ gridSize  + " is " + maxScore);
    result.put(selected,maxScore);
    return result;
  }

  /*
  Squares of sides 300 = 1;
  Squares of sides 299 =

   */
}
