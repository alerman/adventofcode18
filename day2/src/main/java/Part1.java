import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1{

    public static void main(String[] args) throws IOException {
        // write your code here

        List<String> input = getInput();
        int numWith2 = 0;
        int numWith3 = 0;
        for(String entry : input)
        {
            Map<Character, Integer> charMap = new HashMap<>();
            for(char character : entry.toCharArray()) {
                if (!charMap.containsKey(character)) {
                    charMap.put(character, 0);
                }
                charMap.put(character, charMap.get(character) + 1);
            }

            boolean counted2 = false;
            boolean counted3 = false;
            for(Integer value : charMap.values())
            {
                if(value.equals(2) && !counted2)
                {
                    numWith2++;
                    counted2 = true;
                }
                if(value.equals(3) && !counted3)
                {
                    numWith3++;
                    counted3 = true;
                }
            }
        }

        System.out.println(numWith2*numWith3);
    }

    protected static  List<String> getInput() throws IOException {
        File input = new File("/Users/awlerma/git/adventofcode/src/com/company/day2/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        List<String> list = new ArrayList();
        while((line = reader.readLine()) !=null)
        {
            list.add(line);
        }

        return list;
    }
}