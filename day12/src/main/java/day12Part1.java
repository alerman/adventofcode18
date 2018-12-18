
import com.sun.xml.internal.ws.util.StringUtils;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.HashMap;
import java.util.Map;

public class day12Part1 {
  public static void main(String[] args) {
    String currentBoard = "..........#......##...#.#.###.#.##..##.#.....##....#.#.##.##.#..#.##........####.###.###.##..#....#...###.##........................................................................................................................";

    //example
//    currentBoard = "..........#..#.#..##......###...###.................................................";
    Map<String,String> rules = new HashMap<>();
    rules.put("##.##","#");
    rules.put("..##.","#");
    rules.put("##...","#");
    rules.put("..#..","#");
    rules.put(".###.","#");
    rules.put(".#.#.","#");
    rules.put("#..##","#");
    rules.put(".##.#","#");
    rules.put("#.###","#");
    rules.put(".##..","#");
    rules.put("#.#.#","#");
    rules.put(".#...","#");
    rules.put(".#..#","#");
    rules.put("..#.#","#");
    rules.put("...#.","#");
    rules.put("####.","#");
    rules.put("###..","#");

    //example rules
//    rules.put("...##","#");
//    rules.put("..#..","#");
//    rules.put(".#...","#");
//    rules.put(".#.#.","#");
//    rules.put(".#.##","#");
//    rules.put(".##..","#");
//    rules.put(".####","#");
//    rules.put("#.#.#","#");
//    rules.put("#.###","#");
//    rules.put("##.#.","#");
//    rules.put("##.##","#");
//    rules.put("###..","#");
//    rules.put("###.#","#");
//    rules.put("####.","#");
    for(long i=0; i<20;i++)
    {
      StringBuffer newBoard = new StringBuffer();
      for(int index=0; index<currentBoard.length();index++)
      {
        StringBuffer comparable = new StringBuffer();

        getchar(currentBoard, index-2, comparable);
        getchar(currentBoard, index-1, comparable);
        getchar(currentBoard, index, comparable);
        getchar(currentBoard, index+1, comparable);
        getchar(currentBoard, index+2, comparable);


        String result = rules.get(comparable.toString());
        if("#".equals(result)) {
          newBoard.append(result);
          if(index == currentBoard.length()-2)
          {
            newBoard.append(".");
          }
          if(index == currentBoard.length()-1)
          {
            newBoard.append("..");
          }
        }else
        {
          newBoard.append(".");
        }
      }
      currentBoard = newBoard.toString();
      int sum=0;
      for(int j=-10;j<currentBoard.length()-11;j++)
      {
        if("#".equals(currentBoard.substring(j+10,j+11)))
        {
          sum= sum + j;
        }
      }
      System.out.println(i+ ": " + sum);

    }

    int sum=0;
    for(int i=-10;i<currentBoard.length()-11;i++)
    {
      if("#".equals(currentBoard.substring(i+10,i+11)))
      {
        sum= sum + i;
      }
    }
    System.out.println(sum);
  }

  private static void getchar(String currentBoard, int index, StringBuffer comparable) {
     if(currentBoard.length() <= index || index<0)
    {
      comparable.append(".");
    }else {
       comparable.append(currentBoard.charAt(index));
     }
  }
}
//.#....##....#####...#######....#.#..##.
//.#....##....#####...#######....#.#..##..
