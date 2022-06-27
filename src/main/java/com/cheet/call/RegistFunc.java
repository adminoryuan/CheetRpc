package com.cheet.call;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yh
 * @date 2022/6/22 下午5:42
 */
public class RegistFunc {

    private  Map<String, Method> MethodMap=new HashMap<>();

    private volatile static RegistFunc func;

    private RegistFunc(){}
    public static RegistFunc getInstance(){
        if (func==null){
            synchronized (RegistFunc.class){
                func=new RegistFunc();
            }
        }
        return func;
    }
    public void Regist(Class cls){
        String names=cls.getCanonicalName();
        String prifix=names.replaceAll(cls.getPackage().getName(),"");
        System.out.println(prifix);
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            MethodMap.put(prifix+"."+method.getName(),method);

        }
    }

    public Method getRegistMethod(String methodName){
        return MethodMap.get(methodName);
    }
}
