import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class day8_part1 {
    private static int index = 0;
    public static void main(String[] args) throws IOException {
        File input = new File("/home/alerman/IdeaProjects/adventofcode18/day8/src/main/resources/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;

        line = reader.readLine();
        String[] lineSplit = line.split(" ");
        int[] intArr = new int[lineSplit.length];

        for (int i = 0; i<lineSplit.length;i++)
        {
            intArr[i] = Integer.parseInt(lineSplit[i]);
        }

        Node node = parseNode(intArr);
        System.out.println(node.getUuid());

        List<Integer> allMetadata = getAllMetadata(node);
        int count = 0;
        for(int i : allMetadata)
        {
            count += i;
        }
        System.out.println(count);

    }

    private static List<Integer> getAllMetadata(Node node) {
        List<Integer> result = node.getMetadataList();
        for(Node innerNode : node.getNodeList())
        {
            result.addAll(getAllMetadata(innerNode));
        }
        return result;
    }

    private static Node parseNode(int[] intArr) {
        int numNodes = intArr[index];
        index++;
        int numMetadata = intArr[index];
        index++;

        Node node = new Node();
        node.setUuid(UUID.randomUUID());
        int processed = 0;
        while(processed<numNodes)
        {
            Node newNode = parseNode( intArr);
            node.getNodeList().add(newNode);
            processed++;
            newNode.count = 2 + newNode.getMetadataList().size() + newNode.getNodeList().size();
           // index = index + newNode.count;
        System.out.println(index);
        }
        processed = 0;
        while(processed<numMetadata)
        {
            node.getMetadataList().add(intArr[index+processed]);
            processed++;
        }

        index = index+processed;

        return node;
    }
}
