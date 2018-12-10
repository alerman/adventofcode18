import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class day10_part1 {

  public static void main(String[] args) throws IOException {
    File input = new File("/home/awlerma/git/adventofcode18/day10/src/main/resources/input.txt");
    BufferedReader reader = new BufferedReader(new FileReader(input));
    String line;
    List<Star> stars = new ArrayList<>();
    Map<Integer, Integer> iterationToScore = new HashMap<>();
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;
    int maxY = Integer.MIN_VALUE;
    while((line = reader.readLine()) !=null)
    {
      String[] lineSplit = line.split("v");
      String positionString = lineSplit[0];
      String velocityString = lineSplit[1];
      //between < and , = position x
      //between , and > = position y
      int xPos = Integer.parseInt(positionString.substring(positionString.indexOf('<')+1,positionString.indexOf(",")).trim());
      int yPos = Integer.parseInt(positionString.substring(positionString.indexOf(',')+1,positionString.indexOf(">")).trim());
      minX = Math.min(xPos, minX);
      minY = Math.min(yPos, minY);
      maxX = Math.max(xPos, maxX);
      maxY = Math.max(yPos, maxY);


      int xVel = Integer.parseInt(velocityString.substring(velocityString.indexOf('<')+1,velocityString.indexOf(",")).trim());
      int yVel = Integer.parseInt(velocityString.substring(velocityString.indexOf(',')+1,velocityString.indexOf(">")).trim());

      Star star = new Star(new Point(xPos,yPos),xVel,yVel);
      stars.add(star);
    }
    int iteration = 50000;
    for(int i = 0; i<iteration; i++)
    {
      for(Star s : stars)
      {
        s.applyMove();
      }
    }

    int score  = Integer.MAX_VALUE;
    int lastScore;

    boolean inversion = false;
    while(!inversion)
    {
      lastScore = score;
      System.out.println("Score at start of "+iteration+": " + score);
      minX = Integer.MAX_VALUE;
      minY = Integer.MAX_VALUE;
      maxX = Integer.MIN_VALUE;
      maxY = Integer.MIN_VALUE;
      for(Star star : stars)
      {
        star.unapplyMove();
        minX = Math.min(star.p.x, minX);
        minY = Math.min(star.p.y, minY);
        maxX = Math.max(star.p.x, maxX);
        maxY = Math.max(star.p.y, maxY);
      }
      if(Math.abs(maxX-minX) + Math.abs(maxY-minY) < 500) {
        score = computeScore(stars, minX, minY, maxX, maxY);
      }
      if(score>lastScore)
      {
        inversion = true;
      }
      System.out.println("Score at end of "+iteration+": " + score);

      iteration--;
    }

    Set<Point> points = new HashSet<>();
    for(Star s : stars)
    {
      s.applyMove();
      points.add(s.p);
    }
    for(int y=minY;y<maxY; y++)

    {
      for(int x = minX; x<maxX; x++)
      {
        Point curr = new Point(x,y);
        if(points.contains(curr))
        {
          System.out.print("#");
        } else {
          System.out.print(".");
        }
      }
      System.out.println("");
    }






  }

  private static int computeScore(List<Star> stars, int minX, int minY, int maxX, int maxY) {
    Map<Point,Integer> map = new HashMap<>();
    for(Star star : stars)
    {
      map.put(star.p,0);
    }


    for (int i = minX; i<= maxX; i++){
      for(int j = minY; j<=maxY;j++)
      {
        Point closest = null;
        int smallestDistance = Integer.MAX_VALUE;
        for(Point p : map.keySet())
        {
          int distance = Math.abs(p.x - i) + Math.abs(p.y-j);
          if(distance<smallestDistance && distance != 0)
          {
            smallestDistance = distance;
            closest = p;
          }
          if(closest != p && distance == smallestDistance)
          {
            closest = null;
          }
        }

        if(closest!=null && closest.getX() != minX && closest.y!=minY && closest.x != maxX && closest.y!=minY)
        {
          map.put(closest,map.get(closest)+1);
        }
      }
    }

    int result = 0;

    for(Integer count : map.values())
    {
      result = Math.max(result,count);
    }

    return result;
  }
}
