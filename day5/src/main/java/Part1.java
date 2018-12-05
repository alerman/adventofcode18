import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {

        File input = new File("/home/alerman/IdeaProjects/adventofcode18/day5/src/main/java/input-example.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;

        line = reader.readLine();
        List<Character> inputArr = new ArrayList<>();
        for(char c : line.toCharArray())
        {
            inputArr.add(c);
        }
        int count = countCollapsedChars(inputArr);

        System.out.println("Result:" + count);
    }

    private static int countCollapsedChars(List<Character> inputArr) {
        List<Character> resultList = new ArrayList<>();
        String lastChar = "";
        boolean reset = true;
        boolean noHits =false;
        while(!noHits) {
            noHits = true;
            for (Character c : inputArr) {
                String currChar = c.toString();
                if (reset) {
                    reset = false;
                } else {
                    if (!lastChar.equals(currChar) && lastChar.toUpperCase().equals(currChar.toUpperCase())) {
                        reset = true;
                        noHits = false;
                    } else {
                        resultList.add(lastChar.toCharArray()[0]);
                    }
                }
                lastChar = currChar;
            }
            if(!reset)
            {
                resultList.add(lastChar.toCharArray()[0]);
            }
            inputArr = new ArrayList<>(resultList);
            resultList = new ArrayList<>();
            reset=true;
        }
        return inputArr.size();
    }


}
