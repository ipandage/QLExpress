package engine;

import cn.hutool.core.convert.Convert;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Operator {
    public static boolean evaluate(String expr, Map<String, Object> conditionRet) throws Exception {
        // logicRet
        ExpressRunner runner = new ExpressRunner();
        // 业务的上下文参数
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.putAll(conditionRet);
        // 规则脚本
        expr = "a > b";
        // 规则结果
        Object r = runner.execute(expr, context, null, true, false);
        if (r instanceof Boolean) {
            return (Boolean)r;
        }
        return false;
    }

    public static boolean compare(String operator, Object left, Object right) throws Exception {
        // log.info(String.format("compare operator: %s", operator, left, right));
        if (!Configs.CompareOperators.contains(operator)) {
            // log.error(String.format("not compare operator: %s", operator));
            throw new Exception("ErrorNotSupportOperator");
        }

        switch (operator) {
            case Configs.EQ:
                return equal(left, right);
            case Configs.NEQ:
                boolean rs = equal(left, right);
                return !rs;

            // only number can compare(gt,lt,ge,le)
            case Configs.GT:
                return numCompare(left, right, operator);
            case Configs.LT:
                return numCompare(left, right, operator);
            case Configs.GE:
                return numCompare(left, right, operator);
            case Configs.LE:
                return numCompare(left, right, operator);
        }
        throw new Exception("ErrorNotSupportOperator");
    }

    public static boolean numCompare(Object left, Object right, String op) throws Exception {
        Double leftNum = Convert.toDouble(left);
        if (leftNum == null) {
            throw new Exception();
        }
        Double rightNum = Convert.toDouble(right);
        if (rightNum == null) {
            throw new Exception();
        }
        switch (op) {
            case Configs.EQ:
                return leftNum.equals(rightNum);
            case Configs.NEQ:
                return !leftNum.equals(rightNum);
            case Configs.GT:
                return leftNum > rightNum;
            case Configs.LT:
                return leftNum < rightNum;
            case Configs.GE:
                return leftNum >= rightNum;
            case Configs.LE:
                return leftNum <= rightNum;
            default:
                throw new Exception();
        }
    }

    public static boolean equal(Object left, Object right) throws Exception {
        String leftType = TypeUtil.getType(left);
        if (leftType == null) {
            throw new Exception();
        }
        String rightType = TypeUtil.getType(right);
        if (rightType == null) {
            throw new Exception();
        }
        if (!TypeUtil.matchType(leftType, rightType)) {
            return false;
        }
        if (leftType.equals(Configs.ARRAY)) {
            return arrayEqual((Object[])left, (Object[])right);
        }
        if (leftType.equals(Configs.MAP)) {
            throw new Exception();
        }
        if (leftType.equals(Configs.STRING)) {
            return left.equals(right);
        }
        if (leftType.equals(Configs.BOOL)) {
            Boolean leftBool = Convert.toBool(left);
            if (leftBool == null) {
                throw new Exception();
            }
            Boolean rightBool = Convert.toBool(right);
            if (rightBool == null) {
                throw new Exception();
            }
            return leftBool.equals(rightBool);
        }
        if (leftType.equals(Configs.INT) || leftType.equals(Configs.FLOAT)) {
            Double leftNum = Convert.toDouble(left);
            if (leftNum == null) {
                throw new Exception();
            }
            Double rightNum = Convert.toDouble(right);
            if (rightNum == null) {
                throw new Exception();
            }
            return numCompare(leftNum, rightNum, Configs.EQ);
        }
        if (leftType.equals(Configs.DATE)) {
            Date leftDate = Convert.toDate(left);
            if (leftDate == null) {
                throw new Exception();
            }
            Date rightDate = Convert.toDate(right);
            if (rightDate == null) {
                throw new Exception();
            }
            return leftDate.equals(rightDate);
        }
        if (leftType.equals(Configs.DEFAULT)) {
            return left.equals(right);
        }
        throw new Exception();
    }

    public static boolean arrayEqual(Object[] a, Object[] b) {
        if (a.length != b.length) {
            return false;
        }
        if ((a == null) != (b == null)) {
            return false;
        }
        b = Arrays.copyOf(b, a.length);
        Map<Object, Integer> tmp = new HashMap<>(b.length);
        for (Object v : b) {
            tmp.merge(v, 1, Integer::sum);
        }
        for (Object v : a) {
            Integer count = tmp.get(v);
            if (count == null || count == 0) {
                return false;
            }
            tmp.put(v, count - 1);
        }
        return true;
    }

}
