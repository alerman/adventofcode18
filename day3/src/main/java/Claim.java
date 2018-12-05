import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Claim {
    int id;
    Set<Point> points = new HashSet<>();
    String origString = "";

    public Claim(String claimString) {
        origString = claimString;
        String[] splitString = claimString.split(" ");
        id = Integer.parseInt(splitString[0].replace("#", ""));
        String[] splitStartLocations = splitString[2].replace(":", "").split(",");
        int x = Integer.parseInt(splitStartLocations[0]);
        int y = Integer.parseInt(splitStartLocations[1]);
        String[] splitLengths = splitString[3].split("x");
        int lengthX = Integer.parseInt(splitLengths[0]);
        int lengthY = Integer.parseInt(splitLengths[1]);

        for (int i = x; i < x + lengthX; i++) {
            for (int j = y; j < y + lengthY; j++) {
                points.add(new Point(i, j));
            }
        }

    }

    public static void main(String[] args) {

        String test = "#1 @ 1,3: 4x4";
        Claim c = new Claim(test);
        System.out.println(c);

    }
}
