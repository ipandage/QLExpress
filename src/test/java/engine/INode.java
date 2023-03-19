package engine;

public interface INode {
    String getName();
    NodeType getType();
    NodeInfo getInfo();
    void beforeParse(PipelineContext pipelineContext) throws Exception;
    NodeResult parse(PipelineContext pipelineContext) throws Exception;
    void afterParse(PipelineContext pipelineContext, NodeResult nodeResult) throws Exception;
}
