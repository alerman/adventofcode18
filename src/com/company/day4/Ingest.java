import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Ingest {
  public static Map<Date,String> getEvents() throws IOException, ParseException {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    File input = new File("/home/awlerma/git/adventofcode18/src/com/company/day4/input.txt");
    BufferedReader reader = new BufferedReader(new FileReader(input));
    String line;
    Map<Date,String> map = new TreeMap<>();
    while((line = reader.readLine()) !=null)
    {
      String dateString = line.substring(1,line.indexOf("]"));
      System.out.print("[" + dateString + "] ");
      Date d = sdf.parse(dateString);

      String action = line.substring(line.indexOf("]")+2);
      System.out.println(action);

      map.put(d,action);

    }

    return map;
  }

  public static Map<Integer,int[]> getSleepMinutesMap(Map<Date,String> events)
  {
    int asleepMin = 0;
    Map<Integer,int[]> sleepMins = new HashMap<>();
    int[] currentSleepMins = new int[60];
    int currentGuard = -1;
    for(Date d : events.keySet())
    {
      String event = events.get(d);
      if(event.contains("begins"))
      {
        //guard starts shift
        if(currentGuard != -1)
        {
          sleepMins.put(currentGuard,currentSleepMins);
        }

        currentGuard = Integer.valueOf(event.split(" ")[1].replace("#",""));
        if(!sleepMins.containsKey(currentGuard) ){
        currentSleepMins = new int[60];
        for (int i = 0; i < 60; i++) {
          currentSleepMins[i] = 0;
        }
      }else{
          currentSleepMins = sleepMins.get(currentGuard);
        }
      }
      if(event.contains("falls"))
      {
        asleepMin = d.getMinutes();
      }
      if(event.contains("wakes"))
      {
        for(int i = asleepMin; i < d.getMinutes(); i++)
        {
          currentSleepMins[i]++;
        }
      }
    }

    return sleepMins;
  }

  public static void main(String[] args) throws IOException, ParseException {
    Map<Date,String> events = getEvents();
    Map<Integer,int[]> sleepMins = getSleepMinutesMap(events);
  }
}
