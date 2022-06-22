package com.cheet.call;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yh
 * @date 2022/6/22 下午3:12
 */
public class ExecFuncImpl implements ExecFunc{

    RegistFunc func=RegistFunc.getInstance();
    @Override
    public Object Exec(String Method, Object[] args) {


        Object result=null;
        try {
            Method registMethod = func.getRegistMethod(Method);

            Object o = registMethod.getDeclaringClass().getConstructor().newInstance();


            result = registMethod.invoke(o,args[0],args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
