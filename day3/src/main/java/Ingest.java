import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ingest {

    protected static List<Claim> getInput() throws IOException {
        File input = new File("/Users/awlerma/git/adventofcode/src/com/company/day3/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        List<Claim> list = new ArrayList();
        while((line = reader.readLine()) !=null)
        {
            list.add(new Claim(line));
        }

        return list;
    }
}
