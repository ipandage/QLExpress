package engine;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

public class Feature implements IFeature{
    private int id;
    private String name;
    private String tag;
    private String label;
    private String kind;

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
