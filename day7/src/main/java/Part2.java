
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
import java.util.TreeMap;
import java.util.TreeSet;

public class Part2 {
  public static void main(String[] args) throws IOException {
    File input = new File("/home/awlerma/git/adventofcode18/day7/src/main/resources/input.txt");
    BufferedReader reader = new BufferedReader(new FileReader(input));
    String line;
    Map<Character,Set<Character>> map = new TreeMap<>();
    Map<Character,Integer> delay = new HashMap<>();
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
    int i=1;
    for(Character c : map.keySet() )
    {
      delay.put(c,i);
      i++;
    }

    char[] workers = new char[]{0,0,0,0,0};
    int[] counts = new int[]{0,0,0,0,0};
    int counter = 0;
    while(map.size() > 0 || stillWorking(workers)) {

      counter++;
      for(i=0;i<counts.length;i++)
      {
        if(workers[i]!=0) {
          int reset = delay.get(workers[i]) +60;
          if (counts[i] == reset) {
            for (Character c : map.keySet()) {
              map.get(c).remove( workers[i]);
            }
            workers[i] = 0;
            counts[i] = 1;

          } else {
            counts[i]++;
          }
        }
      }

      TreeSet<Character> readyChars = new TreeSet<>();
      for (Character c : map.keySet()) {
        if (map.get(c).size() == 0) {
          readyChars.add(c);
        }
      }
      while(readyChars.size()>0 && anyFree(workers)) {
        for (int j = 0; j < workers.length; j++) {
          if (workers[j] == 0) {
              char acting = readyChars.first();
              readyChars.remove(acting);
              workers[j] = acting;
              counts[j] = 1;
              map.remove(acting);
          }
          if(readyChars.size() == 0)
          {
            break;
          }


        }
      }


      System.out.print(String.valueOf(workers[0]));
      System.out.println(String.valueOf(workers[1]));
    }

    System.out.println(counter-1);
  }

  public static  boolean stillWorking(char[] workers){
    for(char worker: workers)
    {
      if(worker!=0)
      {
        return true;
      }
    }

    return false;
  }

  public static  boolean anyFree(char[] workers){
    for(char worker: workers)
    {
      if(worker==0)
      {
        return true;
      }
    }

    return false;
  }
}
