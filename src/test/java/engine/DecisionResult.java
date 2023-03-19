package engine;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DecisionResult {
    private Map<String, Rule> hitRules;
    private List<Track> tracks;
    private Map<String, IFeature> features;
    private Map<String, NodeResult> nodeResults;
}
