package engine;

import java.util.Map;
import lombok.Data;

@Data
public class EngineRunRequest {
    private String key;
    private String version;
    private String reqId;
    private long uid;
    private Map<String, Object> features;
}
