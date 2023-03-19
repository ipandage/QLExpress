package engine;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Configs {
    public static final String GT = "GT";
    public static final String LT = "LT";
    public static final String GE = "GE";
    public static final String LE = "LE";
    public static final String EQ = "EQ";
    public static final String NEQ = "NEQ";
    public static final String BETWEEN = "BETWEEN";
    public static final String LIKE = "LIKE";
    public static final String IN = "IN";
    public static final String CONTAIN = "CONTAIN";
    public static final String BEFORE = "BEFORE";
    public static final String AFTER = "AFTER";
    public static final String KEYEXIST = "KEYEXIST";
    public static final String VALUEEXIST = "VALUEEXIST";
    public static final String AND = "and";
    public static final String OR = "or";

    public static final Map<String, String> OperatorMap;
    public static final Map<String, String> BooleanOperators;

    static {
        Map<String, String> map = new HashMap<>();
        map.put(GT, ">");
        map.put(LT, "<");
        map.put(GE, ">=");
        map.put(LE, "<=");
        map.put(EQ, "==");
        map.put(NEQ, "!=");
        map.put(BETWEEN, "between");
        map.put(LIKE, "like");
        map.put(IN, "in");
        map.put(CONTAIN, "contain");
        map.put(BEFORE, "before");
        map.put(AFTER, "after");
        map.put(KEYEXIST, "keyexist");
        map.put(VALUEEXIST, "valueexist");
        map.put(AND, "&&");
        map.put(OR, "||");

        OperatorMap = Collections.unmodifiableMap(map);

        BooleanOperators = Collections.unmodifiableMap(Collections.singletonMap(AND, OperatorMap.get(AND)));
        BooleanOperators.put(OR, OperatorMap.get(OR));
    }

    public static final Set<String> NumSupportOperator = CollUtil.newHashSet(
        GT, LT, GE, LE, EQ, NEQ, BETWEEN, IN
    );
    public static final Set<String> StringSupportOperator = CollUtil.newHashSet(
        EQ, NEQ, LIKE, IN
    );
    public static final Set<String> EnumSupportOperator = CollUtil.newHashSet(
        EQ, NEQ
    );
    public static final Set<String> BoolSupportOperator = CollUtil.newHashSet(
        EQ, NEQ
    );
    public static final Set<String> DateSupportOperator = CollUtil.newHashSet(
        BEFORE, AFTER, EQ, NEQ, BETWEEN
    );
    public static final Set<String> ArraySupportOperator = CollUtil.newHashSet(
        EQ, NEQ, CONTAIN, IN
    );
    public static final Set<String> MapSupportOperator = CollUtil.newHashSet(
        KEYEXIST, VALUEEXIST
    );
    public static final Set<String> DefaultSupportOperator = CollUtil.newHashSet(
        EQ, NEQ
    );

    public static final Set<String> CompareOperators = CollUtil.newHashSet(
        EQ, NEQ, GT, LT, GE, LE
    );

    // all support node
    public static final String START = "start";
    public static final String END = "end";
    public static final String RULESET = "ruleset";
    public static final String ABTEST = "abtest";
    public static final String CONDITIONAL = "conditional";
    public static final String DECISIONTREE = "decisiontree";
    public static final String DECISIONMATRIX = "decisionmatrix";
    public static final String SCORECARD = "scorecard";

    // matrix
    public static final String MATRIXX = "matrixX";
    public static final String MATRIXY = "matrixY";

    // all type
    public static final String INT = "int";
    public static final String FLOAT = "float";
    public static final String STRING = "string";
    public static final String BOOL = "bool";
    public static final String DATE = "date";
    public static final String ARRAY = "array";
    public static final String MAP = "map";
    public static final String DEFAULT = "default";

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss";



}
