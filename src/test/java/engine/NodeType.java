package engine;

import java.util.HashMap;
import java.util.Map;

public enum NodeType {
    TypeStart,
    TypeEnd,
    TypeRuleset,
    TypeAbtest,
    TypeConditional,
    TypeTree,
    TypeMatrix,
    TypeScorecard;

    private static final Map<NodeType, String> nodeStrMap = new HashMap<NodeType, String>() {{
        put(TypeStart, "start");
        put(TypeEnd, "end");
        put(TypeRuleset, "ruleset");
        put(TypeAbtest, "abtest");
        put(TypeConditional, "conditional");
        put(TypeTree, "tree");
        put(TypeMatrix, "matrix");
        put(TypeScorecard, "scorecard");
    }};

    public String toString() {
        return nodeStrMap.get(this);
    }

    private static final Map<String, NodeType> nodeTypeMap = new HashMap<String, NodeType>() {{
        put("start", TypeStart);
        put("end", TypeEnd);
        put("ruleset", TypeRuleset);
        put("abtest", TypeAbtest);
        put("conditional", TypeConditional);
        put("tree", TypeTree);
        put("matrix", TypeMatrix);
        put("scorecard", TypeScorecard);
    }};

    public static NodeType getNodeType(String name) {
        return nodeTypeMap.get(name);
    }

}
