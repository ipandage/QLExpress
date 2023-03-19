package engine;

import java.util.List;
import lombok.Data;

@Data
public class BlockStrategy {
    private boolean isBlock;
    private List<String> hitRule;
    private String operator;
    private Object value;
}
