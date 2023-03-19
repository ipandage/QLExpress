package engine;

import lombok.Data;

@Data
public class FlowNode {
    private String nodeName;
    private String nodeKind;
    private String nextNodeName;
    private String nextNodeKind;
    private INode elem;
    private FlowNode nextNode;

    public NodeResult parse(PipelineContext ctx) throws Exception {
        // before hook
        this.elem.beforeParse(ctx);

        // parse
        NodeResult result = this.elem.parse(ctx);

        // after hook
        this.elem.afterParse(ctx, result);

        return result;
    }

    public NodeType getNodeType(FlowNode flowNode) {
        return getNodeType(this.getNodeKind());
    }

    public NodeType getNextNodeType(FlowNode flowNode) {
        return getNodeType(flowNode.getNextNodeKind());
    }

    public static NodeType getNodeType(String name) {
        return NodeType.getNodeType(name);
    }

    public NodeType getNodeType() {
        return NodeType.getNodeType(this.nodeKind);
    }

}
