package engine;

import java.util.Map;
import lombok.Data;

@Data
public class Kernel {
    private Map<String, DecisionFlow> decisionFlowMap;

    public DecisionFlow getDecisionFlow(String key, String version) throws Exception {
        String mapKey = getMapKey(key, version);
        if (getDecisionFlowMap().containsKey(mapKey)) {
            return getDecisionFlowMap().get(mapKey);
        }
        throw new Exception("DecisionFlow not found");
    }

    public String getMapKey(String key, String version) {
        return String.format("%s-%s", key, version);
    }
}
