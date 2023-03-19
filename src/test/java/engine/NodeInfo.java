package engine;

import java.util.List;
import lombok.Data;

@Data
public class NodeInfo {

    private long id;
    private String name;
    private String tag;
    private String label;
    private String kind;
    private List<String> depends;

}
