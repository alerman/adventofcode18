import java.io.*;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws IOException {

        File input = new File("/home/alerman/IdeaProjects/adventofcode18/day5/src/main/java/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;

        line = reader.readLine();
        List<Character> inputArr = new ArrayList<>();
        Set<Character> seenChars = new HashSet<>();

        for(char c : line.toCharArray())
        {
            seenChars.add(String.valueOf(c).toUpperCase().toCharArray()[0]);
        }
        int minCount = Integer.MAX_VALUE;
        Character oneToRemove = 0;

        for(Character remove : seenChars)
        {
            String editedLine = line.replaceAll(remove.toString(),"");
            editedLine = editedLine.replaceAll(remove.toString().toLowerCase(), "");
            for(char c : editedLine.toCharArray())
            {
                inputArr.add(c);
            }


            int count = countCollapsedChars(inputArr);

            if(minCount>count)
            {
                    minCount = count;
                    oneToRemove = remove;
            }
            inputArr = new ArrayList<>();


        }


        System.out.println("Result:" + minCount);
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
