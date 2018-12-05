import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class Part2 {
  public static void main(String[] args) throws IOException, ParseException {
    Map<Date,String> events = Ingest.getEvents();
    Map<Integer,int[]> sleepMins = Ingest.getSleepMinutesMap(events);

    //in this map, which key has the most repeated, and what is it
    int maxAsleepMins = 0;
    int minToChoose = 0;
    int guardToChoose = 0;
    for(Integer guard : sleepMins.keySet())
    {
      int[] asleepMins = sleepMins.get(guard);
      for(int i : asleepMins)
      {
        maxAsleepMins = Math.max(i, maxAsleepMins);
      }
    }

    for(Integer guard : sleepMins.keySet())
    {
      int[] asleepMins = sleepMins.get(guard);
      for(int i =0; i<asleepMins.length;i++)
      {
        int num = asleepMins[i];
        if(num == 17)
        {
          System.out.println(guard + " " + i +": " + guard*i);
        }
      }
    }

    System.out.println(maxAsleepMins);

  }
}
