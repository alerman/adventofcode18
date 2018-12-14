import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("/home/alerman/IdeaProjects/adventofcode18/dat13_secondtry/src/main/resources/input-example-2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        Map<Point, Track> board = new HashMap<>();
        Set<Cart> carts = new TreeSet<>();

        int y=0;
        while((line = reader.readLine()) != null)
        {

            char[] charLine = line.toCharArray();
            for(int x=0;x<charLine.length;x++)
            {
                Point p = new Point(x,y);
                char currentChar = charLine[x];
                //options < > ^ v - \ / |
                switch (currentChar){
                    case '<':
                        carts.add(new Cart(p, Cart.Direction.LEFT));
                        board.put(p,new Track(p,Track.TrackType.HORIZONTAL));
                        board.get(p).setHasCart(true);
                        break;
                    case '>':
                        carts.add(new Cart(p, Cart.Direction.RIGHT));
                        board.put(p,new Track(p,Track.TrackType.HORIZONTAL));
                        board.get(p).setHasCart(true);
                        break;
                    case '^':
                        carts.add(new Cart(p, Cart.Direction.UP));
                        board.put(p,new Track(p,Track.TrackType.VERTICAL));
                        board.get(p).setHasCart(true);
                        break;
                    case 'v':
                        carts.add(new Cart(p, Cart.Direction.DOWN));
                        board.put(p,new Track(p,Track.TrackType.VERTICAL));
                        board.get(p).setHasCart(true);
                        break;
                    case '/':
                        board.put(p, new Track(p,Track.TrackType.LEFT_CORNER));
                        break;
                    case '\\':
                        board.put(p,new Track(p,Track.TrackType.RIGHT_CORNER));
                        break;
                    case '+':
                        board.put(p,new Track(p,Track.TrackType.INTERSECTION));
                        break;
                    case '-':
                        board.put(p,new Track(p,Track.TrackType.HORIZONTAL));
                        break;
                    case '|':
                        board.put(p,new Track(p,Track.TrackType.VERTICAL));
                        break;
                }
            }

            y++;
        }

        while(carts.size()>1)
        {
            Set<Cart> toRemove = new TreeSet<>();
            for(Cart c : carts)
            {
                if(!c.applyMove(board))
                {
                    toRemove.add(c);
                    board.get(c.position).setHasCart(false);
                }
            }
            carts.removeAll(toRemove);
        }
        System.out.println("BLAH");
    }
}
