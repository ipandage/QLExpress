package engine;

import java.util.Map;

public interface IFeature {
    String getName();
    void setValue(Object value) throws Exception;
    Object getValue();
    FeatureType getType();
    Map<String, Object> supportOperators();
    boolean compare(String op, Object value) throws Exception;
}
