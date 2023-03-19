package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Rule {

    private String id;
    private String name;
    private String tag;
    private String label;
    private String kind;
    private List<Condition> conditions;
    private Decision decision;

    public Output parse(PipelineContext ctx, Map<String, IFeature> depends) throws Exception {
        Output output = new Output();
        output.setName(this.getDecision().getOutput().getName());
        output.setKind(this.getDecision().getOutput().getKind());
        output.setHit(false); // default value

        //rule.Conditions
        if (this.getConditions().isEmpty()) {
            String errMsg = String.format("rule (%s) condition is empty", this.getName());
            // log.error(errMsg);
            return output;
        }

        Map<String, Object> conditionRet = new HashMap<>();
        for (Condition condition : this.getConditions()) {
            if (depends.containsKey(condition.getFeature())) {
                IFeature feature = depends.get(condition.getFeature());
                boolean rs;
                try {
                    rs = feature.compare(condition.getOperator(), condition.getValue());
                } catch (Exception e) {
                    // log.error(e.getMessage(), e);
                    return output;
                }
                conditionRet.put(condition.getName(), rs);
            } else {
                //lack of feature  whether ignore
                // log.error("error lack of feature: %s", condition.getFeature());
                //continue
            }
        }

        if (conditionRet.isEmpty()) {
            String errMsg = String.format("rule (%s) condition result error", this.getName());
            // log.error(errMsg);
            return output;
        }

        //rule.Decision
        String expr = this.getDecision().getLogic();
        boolean logicRet = false;
        try {
            logicRet = Operator.evaluate(expr, conditionRet);
        } catch (Exception e) {
            // log.error(e.getMessage(), e);
            return output;
        }
        // log.info("rule result: {} {} {}", rule.getLabel(), rule.getName(), logicRet);
        output.setHit(logicRet);

        //assign
        if (!this.getDecision().getAssign().isEmpty() && logicRet) {
            Map<String, IFeature> features = new HashMap<>();
            for (Map.Entry<String, Object> entry : this.getDecision().getAssign().entrySet()) {
                IFeature feature = FeatureFactory.newFeature(entry.getKey(), FeatureType.TypeDefault); //string
                feature.setValue(entry.getValue());
                features.put(entry.getKey(), feature);
            }
            ctx.setFeatures(features);
        }

        return output;

    }

}
