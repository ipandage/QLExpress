package com.ql.util.express.demo;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class Test {

    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        context.put("a",1);
//        context.put("b",2);
//        context.put("c",3);
//        context.put("d",4);

        context.put("a",true);
        context.put("b",false);
        context.put("c",false);

        String express = "((a + b) * c) + d";

        Object result = runner.execute(express, context, null, true, false);
        System.out.println(express + "的结果：" + result);
    }
}
