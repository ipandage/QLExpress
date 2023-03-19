package engine;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Decision {
    private List<String> depends;
    private String logic;
    private Output output;
    private Map<String, Object> assign;
}
