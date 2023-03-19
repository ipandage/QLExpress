package engine;

import lombok.Data;

@Data
public class Condition {
    private String feature;
    private String operator;
    private Object value;
    private String goTo;
    private String result;
    private String name;
}
