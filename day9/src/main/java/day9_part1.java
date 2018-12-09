import java.util.ArrayList;
import java.util.List;

public class day9_part1 {
    public static void main(String[] args) {
        int numMarbles = 71010*100; //for part 1, remove the * 100
        int numPlayers = 468     ;
        int[] score = new int [numPlayers];
        for(int i = 0;i < numPlayers;i++)
        {
            score[i] = 0;
        }
        List<Integer> board = new ArrayList<>();
        board.add(0,0);
        board.add(1,1);

        int player = 1;
        int currentMarbleAt = 1;
        for(int marble=2; marble<=numMarbles;marble++)
        {
            if(player/numPlayers>0)
            {
                player=0;
            }


            if(marble%23 !=0) {

                if (currentMarbleAt == board.size() - 1) {
                    //marble just before end. Put at spot 1
                    board.add(1, marble);
                    currentMarbleAt = 1;
                } else {
                    if (currentMarbleAt == board.size() - 2) {
                        // put current marble at end
                        board.add(board.size(), marble);
                        currentMarbleAt = board.size()-1;
                    } else {
                        board.add(currentMarbleAt + 2, marble);
                        currentMarbleAt = currentMarbleAt+2;
                    }
                }

            }else
            {
                int addScore = marble;
                //7 marbles to the left of current marble
                if(currentMarbleAt >= 7)
                {
                    int removeMarble = currentMarbleAt -7;
                    addScore = addScore + board.remove(removeMarble);
                    currentMarbleAt = removeMarble;
                }
                else
                {
                    int removeMarble = board.size() + (currentMarbleAt - 7);
                    addScore = addScore + board.remove(removeMarble);
                    currentMarbleAt = removeMarble == board.size() ? 0 : removeMarble;
                }
                score[player] += addScore;
                //currentMarble = currentMarble - 6
            }


            player++;

        }
        int max = 0;
        for(int i = 0; i < numPlayers ; i++)
        {
            max = Math.max(max, score[i]);
        }
        System.out.println(max);
    }
}
