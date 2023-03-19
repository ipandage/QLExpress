package engine;

import cn.hutool.core.convert.Convert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class RulesetNode implements INode {

    private NodeInfo info;
    private String execPlan;
    private BlockStrategy blockStrategy;
    private List<Rule> rules;
    private Map<String, IFeature> dependFeatures;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public NodeType getType() {
        return null;
    }

    @Override
    public NodeInfo getInfo() {
        return null;
    }

    @Override
    public void beforeParse(PipelineContext pipelineContext) throws Exception {

    }

    @Override
    public NodeResult parse(PipelineContext ctx) throws Exception {
        NodeInfo info = this.getInfo();
        NodeResult nodeResult = new NodeResult(); // todo
        HashMap<String, Output> ruleOutputs = new HashMap<>();
        //ruleset 批量调用特征
        Map<String, IFeature> depends = ctx.getFeatures(info.getDepends());

        if (this.getExecPlan().equals("parallel")) { //并发执行规则
            // 本期暂不实现

        } else { //串行执行
            for (Rule rule : this.getRules()) {
                Output output = rule.parse(ctx, depends);
                if (!output.isHit()) {
                    continue;
                }
                //命中规则有结果
                ctx.addHitRule(rule);
                ruleOutputs.put(rule.getName(), output);
            }
        }

        //无规则命中
        if (ruleOutputs.size() == 0) {
            // log.info("ruleset {} hit no rule", rulesetNode.getName());
            return nodeResult;
        }

        HashMap<String, Void> hitRules = new HashMap<>();
        if (this.getBlockStrategy().getHitRule().size() > 0) {
            for (String v : this.getBlockStrategy().getHitRule()) {
                hitRules.put(v, null);
            }
        }

        boolean block = false;
        double score = 0;
        Strategy nodeRt = new Strategy();
        for (String name : ruleOutputs.keySet()) {
            //节点规则得分
            if (Strategy.strategys.containsKey((String) ruleOutputs.get(name).getValue())) {
                Strategy s = Strategy.strategys.get((String) ruleOutputs.get(name).getValue());
                double v = Convert.toDouble(s.getScore());
                score += v;
                //根据优先级获取结果
                if (nodeRt.getPriority() < s.getPriority()) { //默认0
                    nodeRt = s;
                }
            }
            //是否允许提前中断
            if (this.getBlockStrategy().isBlock()) {
                //命中规则在 ruleset.block_strategy.hit_rule 列表中
                if (hitRules.containsKey(name)) {
                    block = true;
                }
            }
        }
        if (this.getBlockStrategy().isBlock()) {
            Boolean ok = Operator.compare(this.getBlockStrategy().getOperator(), nodeRt.getName(),
                this.getBlockStrategy().getValue());
            if (ok) {
                block = true;
            }
        }
        nodeResult.setBlock(block);

        return null;
    }

    @Override
    public void afterParse(PipelineContext pipelineContext, NodeResult nodeResult) throws Exception {

    }
}
