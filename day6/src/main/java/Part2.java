import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Part2 {

public static void main(String[] args) throws IOException {
  File input = new File("/home/awlerma/git/adventofcode18/day6/src/main/resources/input.txt");
  BufferedReader reader = new BufferedReader(new FileReader(input));
  String line;
  Map<Point,Integer> map = new HashMap<>();

  int maxX = 0;
  int minX = Integer.MAX_VALUE;

  int maxY = 0;
  int minY = Integer.MAX_VALUE;

  while((line = reader.readLine()) !=null)
  {
    line = line.replaceAll(" ","");
    String[] lineSplit = line.split(",");
    int x = Integer.valueOf(lineSplit[0]);
    int y = Integer.valueOf(lineSplit[1]);
    map.put(new Point(x,y),0);

    maxX = Math.max(maxX,x);
    minX = Math.min(minX,x);

    maxY = Math.max(maxY,y);
    minY = Math.min(minY,y);
  }

  System.out.println(maxX);

  int size = 0;
  for (int i = minX; i<= maxX; i++){
    for(int j = minY; j<=maxY;j++)
    {
      int totalDistance = 0;
      Collection<Point> points = map.keySet();
      for(Point p : points)
      {
        int distance = Math.abs(p.x - i) + Math.abs(p.y-j);
        totalDistance += distance;
      }

      if(totalDistance < 10000)
      {
        size++;
      }
    }
  }

  System.out.println(size);

}
}
