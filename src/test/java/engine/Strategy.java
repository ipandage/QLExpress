package engine;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Strategy {
    private String name;
    private int priority;
    private int score;

    public Strategy() {

    }

    static Map<String, Strategy> strategys = new HashMap<String, Strategy>();

    static {
        strategys.put("reject", new Strategy("reject", 9, 100));
        strategys.put("approve", new Strategy("approve", 5, 5));
        strategys.put("record", new Strategy("record", 1, 1));
    }

}
