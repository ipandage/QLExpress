package engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Track {
    private long id;
    private String name;
    private String label;
    private String tag;
    private NodeType kind;
}
