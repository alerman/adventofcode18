import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class Part1 {
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
      int sumAsleep = 0;
      for(int i : asleepMins)
      {
        sumAsleep +=i;
      }

      if(sumAsleep>maxAsleepMins)
      {
        maxAsleepMins = sumAsleep;
        guardToChoose = guard;
      }
    }

    int[] minsToLookAt = sleepMins.get(guardToChoose);
    int num = 0;
    for(int i = 0;i<minsToLookAt.length;i++)
    {
      if(minsToLookAt[i]>num)
      {
        num = minsToLookAt[i];
        minToChoose = i;
      }
    }

    System.out.println(minToChoose + " " + guardToChoose + ": " + guardToChoose*minToChoose);

  }
}
