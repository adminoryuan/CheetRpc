package com.cheet.Codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author yh
 * @date 2022/6/22 下午2:39
 */
public class SerialiZerTools {
    public static Object byteToObject( byte[] bytes) {
        Object obj = null;
        try {
            //bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();

            bi.close();
            oi.close();
        }
        catch(Exception ae) {
            ae.printStackTrace();
        }
        return obj;
    }


    public static byte[] objectToByte(Object obj)
    {
        byte[] bytes = new byte[0];
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        }
        catch(Exception ae) {
           ae.printStackTrace();
        }
        return bytes;
    }
}
