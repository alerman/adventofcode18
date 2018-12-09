import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<Claim> claims = Ingest.getInput();
        HashMap<Point, Integer> pointToCountMap = new HashMap<>();

            TreeMap<Integer, ArrayList<Point>> map = new TreeMap<>();
            for(ArrayList<Point> points : map.values())
            {

            }

        for(Claim c : claims){
            for(Point p : c.points)
            {
                if(!pointToCountMap.containsKey(p))
                {
                    pointToCountMap.put(p,0);
                }
                pointToCountMap.put(p,pointToCountMap.get(p)+1);
            }
        }

        int overlapping = 0;
        for (Integer count : pointToCountMap.values())
        {
            if(count>1)
            {
                overlapping++;
            }
        }

        System.out.println(overlapping);

    }
}
