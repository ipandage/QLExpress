package engine;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class DecisionFlow {
    private String key;
    private String version;
    private Map<String, Object> metadata;
    private String md5;
    private Map<String, FlowNode> flowMap;
    private FlowNode startNode;
    private Map<String, IFeature> featureMap;

    public void run(PipelineContext ctx) throws Exception {
        // recover
        try {
            // find start node
            FlowNode flowNode = getStartNode();
            if (flowNode == null) {
                throw new Exception("no start node");
            }

            boolean gotoNext = true;
            while (gotoNext) {
                // ctx.setCurrentNode(flowNode);
                Map<String, Object> result = parseNode(flowNode, ctx);
                flowNode = (FlowNode) result.get("nextNode");
                gotoNext = (boolean) result.get("gotoNext");
            }
        } catch (Exception e) {
            // LOGGER.severe(e.getMessage());
            throw e;
        }
    }

    public Map<String, Object> parseNode(FlowNode curNode, PipelineContext ctx) {
        Map<String, Object> result = new HashMap<>();

        // parse current node
        ctx.addTrack(curNode.getElem());
        try {
            NodeResult res = curNode.parse(ctx);
            ctx.addNodeResult(curNode.getNodeName(), res);

            // node is block
            if (res.isBlock()) {
                result.put("gotoNext", !res.isBlock());
                return result;
            }

            // goto next node
            switch (curNode.getNodeType()) {
                case TypeEnd: // END:
                    result.put("gotoNext", false);
                    return result;
                case TypeConditional:
//                case TypeAbtest: // ABTEST
//                    FlowNode nextNode = getNode(res.getNextNodeName(), res.getNextNodeType());
//                    result.put("nextNode", nextNode);
//                    result.put("gotoNext", true);
//                    return result;
                default: // start, matrix, ruleset, tree, scorecard
                    FlowNode nextNode = getNode(curNode.getNextNodeName(), curNode.getNextNodeKind());
                    result.put("nextNode", nextNode);
                    result.put("gotoNext", true);
                    return result;
            }
        } catch (Exception e) {
            return result;
        }
    }

    public FlowNode getNode(String name, Object nodeType) {
        String key = getNodeKey(name, nodeType);
        if (flowMap.containsKey(key)) {
            return flowMap.get(key);
        }
        return new FlowNode();
    }

    private String getNodeKey(String name, Object nodeType) {
        return String.format("%s-%s", nodeType.toString(), name);
    }

}
