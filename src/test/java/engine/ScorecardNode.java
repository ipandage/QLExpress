package engine;

import lombok.Data;

@Data
public class ScorecardNode implements INode {
    private NodeInfo info;
    // private List<Block> blocks;
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
    public void beforeParse(PipelineContext pipelineContext) throws Exception {

    }

    @Override
    public NodeResult parse(PipelineContext pipelineContext) throws Exception {
        return null;
    }

    @Override
    public void afterParse(PipelineContext pipelineContext, NodeResult nodeResult) throws Exception {

    }
}
