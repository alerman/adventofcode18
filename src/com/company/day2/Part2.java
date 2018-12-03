package com.company.day2;

import java.io.IOException;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
	// write your code here

        List<String> input = Part1.getInput();
        List<String> input2 = Part1.getInput();
        int diffIndex=0;
        outer:
        for(String entry : input)
        {
            char[] entryChars = entry.toCharArray();
            for(String entry2: input2)
            {
                char[] entry2Chars = entry2.toCharArray();
                int diffSize = 0;
                for(int i=0; i<entryChars.length;i++)
                {
                    if(entryChars[i] != entry2Chars[i])
                    {
                        diffSize++;
                        diffIndex = i;
                    }
                }

                if(diffSize == 1)
                {
                    System.out.println(entry + "\n" + entry2);
                    for(int i=0;i<entryChars.length;i++)
                    {
                        if(i!=diffIndex)
                        {
                            System.out.print(entryChars[i]);
                        }
                    }
                    break outer;
                }


            }

        }
    }
}