package engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class TreeNode implements INode {

    private NodeInfo info;
    private List<Block> blocks;
    private Strategy strategy;

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
//        TreeNode treeNode = this;
//        NodeInfo info = this.getInfo();
//        // log.info("======[trace] Tree {} start======", info.getLabel(), treeNode.getName());
//        NodeResult nodeResult = new NodeResult();
//        Exception resultErr = null;
//        Object result;
//        Map<String, Block> blockMap = treeNode.init();
//
//        List<Feature> depends = ctx.getFeatures(info.getDepends());
//        Block block = blockMap.get(treeNode.getStrategy().getStart());
//        boolean gotoNext = true;
//        while (gotoNext) {
//            Object ret;
//            try {
//                ResultAndNext resultAndNext = block.parse(depends);
//                ret = resultAndNext.getResult();
//                gotoNext = resultAndNext.getGotoNext();
//            } catch (Exception e) {
//                // log.error(e);
//                resultErr = e;
//                break;
//            }
//            if (gotoNext) {
//                Block b = blockMap.get((String)ret);
//                if (b == null) {
//                    // resultErr = errcode.ParseErrorTreeNotMatch;
//                } else {
//                    block = b;
//                }
//            } else { //finish
//                result = ret;
//                break;
//            }
//        }
//
//        //save into ctx feature
//        Kind kind = treeNode.getStrategy().getOutputKind();
//        Feature feature = new Feature(treeNode.getName(), getFeatureType(kind));
//        feature.setValue(result);
//        ctx.setFeature(feature);
//        if (!treeNode.getStrategy().getOutputName().isEmpty()) { //extra
//            Feature extraFeature = new Feature(treeNode.getStrategy().getOutputName(), getFeatureType(kind));
//            extraFeature.setValue(result);
//            ctx.setFeature(extraFeature);
//        }
//
//        //output
//        nodeResult.setValue(result);
//        log.info("======[trace] Tree {} end======", info.getLabel(), treeNode.getName());
        return null;
    }


    @Override
    public void afterParse(PipelineContext pipelineContext, NodeResult nodeResult) throws Exception {

    }

    public Map<String, Block> init() {
        Map<String, Block> blockMap = new HashMap<>();
        for (Block block : blocks) {
            blockMap.put(block.getName(), block);
        }
        return blockMap;
    }

}
