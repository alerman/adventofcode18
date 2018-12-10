
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Part1 {
  public static void main(String[] args) throws IOException {
    File input = new File("/home/awlerma/git/adventofcode18/day7/src/main/resources/input.txt");
    BufferedReader reader = new BufferedReader(new FileReader(input));
    String line;
    Map<Character,Set<Character>> map = new HashMap<>();
    while((line = reader.readLine()) !=null)
    {
      line = line.replaceAll("Step ","");
      line = line.replaceAll(" must be finished before step ","");
      line = line.replaceAll(" ","");

      char[] lineSplit = line.toCharArray();
      char id = lineSplit[1];
      char prereq = lineSplit[0];
      if(map.containsKey(id))
      {
        map.get(id).add(prereq);
      }else
      {
        Set<Character> treeSet = new TreeSet<>();
        treeSet.add(prereq);
        map.put(id,treeSet);
      }
      if(!map.containsKey(prereq))
      {
        Set<Character> treeSet = new TreeSet<>();
        map.put(prereq,treeSet);
      }
    }

    Set<Character> order = new LinkedHashSet<>();
    while(map.size() > 0) {
      TreeSet<Character> readyChars = new TreeSet<>();
      for (Character c : map.keySet()) {
        if (map.get(c).size() == 0) {
          readyChars.add(c);
        }
      }
      if (readyChars.size() > 0) {
        char acting = readyChars.first();
        order.add(acting);
        map.remove(acting);
        for(Character c : map.keySet())
        {
          map.get(c).remove(acting);
        }
      }
    }

   for(Character c : order)
   {
     System.out.print(c);
   }

  }
}
