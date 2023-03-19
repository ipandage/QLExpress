package engine;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class EngineRunResponse {
    private String key;
    private String reqId;
    private long uid;
    private List<Map<String, Object>> features;
    private List<Map<String, Object>> tracks;
    private List<Map<String, Object>> hitRules;
    private List<Map<String, Object>> nodeResults;
    private String startTime;
    private String endTime;
    private long runTime;
}
