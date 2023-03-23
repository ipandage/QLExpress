package engine;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ScorecardNode implements INode {
    private NodeInfo info;
    private List<Block> blocks;
    private Strategy strategy;

    public NodeInfo getInfo() {
        return info;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public NodeType getType() {
        return null;
    }

    @Override
    public void beforeParse(PipelineContext pipelineContext) throws Exception {

    }

    @Override
    public NodeResult parse(PipelineContext ctx) throws Exception {
//        NodeInfo info = this.getInfo();
//        List<Feature> depends = ctx.getFeatures(info.getDepends());
//        NodeResult nodeResult = new NodeResult(info.getId(), info.getName(), this.getType(), info.getTag(), info.getLabel());
//        List<Object> retArr = new ArrayList<>();
//        for (Block block : this.getBlocks()) {
//            Object[] result = block.parse(depends);
//            if (result[2] != null) {
//                // log.error(result[2]);
//                break;
//            }
//            retArr.add(result[0]);
//        }
//
//        //total score
//        double score = 0;
//        if (!retArr.isEmpty()) {
//            UdfFn Fn = Global.getUdf(this.getStrategy().getLogic());
//            Object ret = Fn.invoke(retArr.toArray());
//            if (ret instanceof Throwable) {
//                log.error(ret);
//            } else {
//                score = Util.toFloat64(ret);
//            }
//        }
//
//        //save into ctx feature
//        ScorecardOutputKind kind = this.getStrategy().getOutputKind();
//        Feature feature = newFeature(scorecardNode.getName(), getFeatureType(kind));
//        feature.setValue(score);
//        ctx.setFeature(feature);
//        if (this.getStrategy().getOutputName() != null && !scorecardNode.getStrategy().getOutputName().isEmpty()) { //extra
//            Feature extraFeature = newFeature(scorecardNode.getStrategy().getOutputName(), getFeatureType(kind));
//            extraFeature.setValue(score);
//            ctx.setFeature(extraFeature);
//        }
//
//        //output
//        nodeResult.setValue(score);
//        nodeResult.setScore(score);
//        log.info(String.format("======[trace] Scorecard %s end======", info.getLabel(), this.getName()));
//        return nodeResult;
        return null;
    }


    @Override
    public void afterParse(PipelineContext pipelineContext, NodeResult nodeResult) throws Exception {

    }
}
