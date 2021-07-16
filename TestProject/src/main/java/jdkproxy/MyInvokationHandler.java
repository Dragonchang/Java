package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvokationHandler implements InvocationHandler {

    private Object obj;
    public MyInvokationHandler(Object object){
        this.obj = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method: "+method.getName()+" args: "+args[0]);
        Object result;
        if(method.getName().contains("get")){
            System.out.println("...get Method Executing...");
        }else{
            System.out.println("...set Method Executing...");
        }
        result = method.invoke(obj, args);
        return result;
    }
}
