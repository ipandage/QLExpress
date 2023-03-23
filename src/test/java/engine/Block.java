package engine;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Block {
    private String name;
    private String feature;
    private List<Condition> conditions;

    public Object[] parse(Map<String, IFeature> depends) throws Exception {
        if (this.getConditions() == null || this.getConditions().size() == 0) {
            return new Object[] { null, false, null };
        }
        for (Condition condition : this.getConditions()) {
            if (depends.containsKey(this.getFeature())) {
                IFeature feature = depends.get(this.getFeature());
                Object v = feature.getValue();
                if (v == null) {
                    // log.error(String.format("feature %s empty", feature.getName()));
                    continue;
                }
                boolean hit = feature.compare(condition.getOperator(), condition.getValue());
                if (hit) {
                    if (!condition.getGoTo().isEmpty()) {
                        return new Object[] { condition.getGoTo(), true, null };
                    } else {
                        return new Object[] { condition.getResult(), false, null };
                    }
                }
            } else {
                // log.error(String.format("lack of feature: %s", this.getFeature()));
                continue;
            }
        }
        return null;
    }
}
