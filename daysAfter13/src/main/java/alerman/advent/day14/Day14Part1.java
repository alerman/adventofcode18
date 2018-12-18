package alerman.advent.day14;

import java.util.ArrayList;
import java.util.List;

public class Day14Part1 {
    public static void main(String[] args) {
        List<Integer> recipeList = new ArrayList<>();
        //Init the recipeList
        recipeList.add(3);
        recipeList.add(7);

        int elf1Index = 0;
        int elf2Index = 1;

        while(recipeList.size()<330121+11)
        {
            int elf1Recipe = recipeList.get(elf1Index);
            int elf2Recipe = recipeList.get(elf2Index);

            int newRecipe = elf1Recipe + elf2Recipe;
            if(newRecipe<10)
            {
                recipeList.add(newRecipe);
            }else{
                recipeList.add(1);
                recipeList.add(newRecipe % 10);
            }

            for(int i=0;i<elf1Recipe+1;i++)
            {
                elf1Index = elf1Index +1;
                if(elf1Index == recipeList.size()) {
                    elf1Index = 0;
                }
            }

            for(int i=0;i<elf2Recipe+1;i++)
            {
                elf2Index = elf2Index +1;
                if(elf2Index==recipeList.size()) {
                    elf2Index = 0;
                }
            }

        }

        int index = 330121;
        for (int i=0; i<10;i++)
        {
            System.out.print(recipeList.get(index));
            index = index+1;

        }



        //System.out.println(recipeList);


    }
}
