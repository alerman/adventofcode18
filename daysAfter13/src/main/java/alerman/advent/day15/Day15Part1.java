package alerman.advent.day15;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day15Part1 {
    public static void main(String[] args) throws IOException {
        List<String> initialBoard = Files.readAllLines(Paths.get("/home/alerman/IdeaProjects/adventofcode18/daysAfter13/src/main/resources/day15Part1-example.txt"));
        Comparator<Point> pointComparator = new Comparator<Point>() {
            @Override
            public int compare(Point point, Point t1) {
                if(t1.y!=point.y)
                {
                    return Double.compare(point.y, t1.y);
                }
                else return Double.compare(point.x, t1.x);
            }
        };
        int y=0;
        Map<Point,BoardSection> board = new TreeMap<>(pointComparator);
        Map<Point, Combatant> combatantMap = new TreeMap<>(pointComparator);
        for(String line : initialBoard)
        {
            char[] charArr = line.toCharArray();
            for(int x = 0; x<charArr.length; x++)
            {
                Point p = new Point(x,y);
                BoardSection section = new BoardSection();
                if(charArr[x] == '#')
                {
                    section.isWall=true;
                }
                if(charArr[x] == 'E')
                {
                   Elf elf = new Elf();
                   section.setCombatant(elf);
                   combatantMap.put(p,elf);
                }
                if(charArr[x] == 'G')
                {
                    Goblin goblin = new Goblin();
                    section.setCombatant(goblin);
                    combatantMap.put(p,goblin);
                }
                board.put(p,section);
            }
            y++;
        }
        for(Combatant c : combatantMap.values())
        {
          System.out.println(c.getCombatantType());
        }
    }
}
