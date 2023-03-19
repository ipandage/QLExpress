package engine;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NodeResult {
    private long Id;
    private String name;
    private String label;
    private String tag;
    private int kind;
    private boolean isBlock;
    private double score;
    private Object value;
    private String nextNodeName;
    private int nextNodeType;
}
