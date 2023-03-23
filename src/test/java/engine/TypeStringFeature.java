package engine;

import lombok.Data;

@Data
public class TypeStringFeature {
    private String name;
    private FeatureType kind;
    private Object value;
    private Object defaultValue;
}
