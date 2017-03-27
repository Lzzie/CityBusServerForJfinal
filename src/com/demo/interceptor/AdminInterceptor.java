package com.demo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import java.util.logging.Logger;

public class AdminInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
//        Logger.getLogger("ActionKey",inv.getActionKey());
        System.out.println("ActionKey="+inv.getActionKey());
        System.out.println("ControllerKey="+inv.getControllerKey());
        System.out.println("ViewPath="+inv.getViewPath());
        System.out.println("MethodName="+inv.getMethodName());
        System.out.println("Args="+inv.getArgs()[0]);
        System.out.println("MethodName="+inv.getMethodName());

    }
}
