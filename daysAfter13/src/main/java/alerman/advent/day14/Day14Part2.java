package alerman.advent.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day14Part2 {
    public static void main(String[] args) {
        List<Integer> recipeList = new ArrayList<>();
        //Init the recipeList
        recipeList.add(3);
        recipeList.add(7);

        int elf1Index = 0;
        int elf2Index = 1;

        List<Integer> searchList = new ArrayList<>();
        searchList.add(3);
        searchList.add(3);
        searchList.add(0);
        searchList.add(1);
        searchList.add(2);
        searchList.add(1);
//        searchList.add(5);
//        searchList.add(1);
//        searchList.add(5);
//        searchList.add(8);
//        searchList.add(9);

        List<Integer> toSearch = new ArrayList<>();

        while(Collections.indexOfSubList(toSearch,searchList) <0)
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

            toSearch = recipeList.subList(Math.max(0,recipeList.size()-10), Math.max(1,recipeList.size()));

        }

        System.out.println(Collections.indexOfSubList(recipeList,searchList));



        //System.out.println(recipeList);


    }
}
