import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
    private UUID uuid = null;
    private List<Node> nodeList = new ArrayList<>();
    private List<Integer> metadataList = new ArrayList<>();
    int count = 0;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Integer> getMetadataList() {
        return metadataList;
    }

    public void setMetadataList(List<Integer> metadataList) {
        this.metadataList = metadataList;
    }
}
