package engine;

import java.util.Map;

public class TypeDefaultFeature implements IFeature{

    private String name;
    private FeatureType kind;
    private Object value;
    private Object defaultValue;

    public TypeDefaultFeature(String name, FeatureType kind) {
        this.name = name;
        this.kind = kind;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setValue(Object value) throws Exception {

    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public FeatureType getType() {
        return null;
    }

    @Override
    public Map<String, Object> supportOperators() {
        return null;
    }

    @Override
    public boolean compare(String op, Object value) throws Exception {
        return false;
    }
}
