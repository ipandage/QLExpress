package engine;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FeatureType {
    TypeInt,
    TypeFloat,
    TypeString,
    TypeBool,
    TypeDate,
    TypeArray,
    TypeMap,
    TypeDefault;

    public static Map<String, FeatureType> FeatureTypeMap = new HashMap<String, FeatureType>() {{
        put("int", TypeInt);
        put("float", TypeFloat);
        put("string", TypeString);
        put("bool", TypeBool);
        put("date", TypeDate);
        put("array", TypeArray);
        put("map", TypeMap);
        put("default", TypeDefault);
    }};

    public static Map<FeatureType, String> FeatureStrMap = new HashMap<FeatureType, String>() {{
        put(TypeInt, "int");
        put(TypeFloat, "float");
        put(TypeString, "string");
        put(TypeBool, "bool");
        put(TypeDate, "date");
        put(TypeArray, "array");
        put(TypeMap, "map");
        put(TypeDefault, "default");
    }};
}