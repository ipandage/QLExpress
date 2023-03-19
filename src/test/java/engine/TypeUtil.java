package engine;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TypeUtil {

    public static String getType(Object val) throws Exception {
        if (val instanceof String) {
//            if (isInt(val)) {
//                return Configs.INT;
//            }
//            if (isFloat(val)) {
//                return Configs.FLOAT;
//            }
//            if (isBool(val)) {
//                return Configs.BOOL;
//            }
//            if (isDate(val)) {
//                return Configs.DATE;
//            }
            return Configs.STRING;
        } else if (val instanceof Integer) {
            return Configs.INT;
        } else if (val instanceof Long) {
            return Configs.INT;
        } else if (val instanceof Float) {
//            if (isFloat32Int((Float)val)) {
//                return Configs.INT;
//            }
            return Configs.FLOAT;
        } else if (val instanceof Double) {
//            if (isFloat64Int((Double)val)) {
//                return Configs.INT;
//            }
            return Configs.FLOAT;
        } else if (val instanceof Boolean) {
            return Configs.BOOL;
        } else if (val instanceof Date) {
            return Configs.DATE;
        } else if (val instanceof List) {
            return Configs.ARRAY;
        } else if (val instanceof Map) {
            return Configs.MAP;
        }
        //array type like [3]int
        if (val.getClass().isArray()) {
            return Configs.ARRAY;
        }
        throw new Exception();
    }

    public static boolean matchType(String typeA, String typeB) {
        if (typeA.equals(Configs.INT)) {
            typeA = Configs.FLOAT;
        }
        if (typeB.equals(Configs.INT)) {
            typeB = Configs.FLOAT;
        }
        return typeA.equals(typeB);
    }

}
