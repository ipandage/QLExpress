package engine;

import lombok.Data;

@Data
public class Output {
    private String name;
    private Object value;
    private String kind;
    private boolean hit;
}
