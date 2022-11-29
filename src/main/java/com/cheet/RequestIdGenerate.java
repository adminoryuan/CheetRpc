package com.cheet;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

    /**
     * @author yh
     * @date 2022/6/20 下午8:21
     * 生成requestid
     */
    public class RequestIdGenerate {
        private static ReadWriteLock reentrantLockew= new ReentrantReadWriteLock();
        static byte reqid=0;
        public static synchronized byte getRequestId(){
           if (reqid==Byte.MAX_VALUE){
               reqid=1;
           }else {
               reqid+=1;
           }
        return  reqid;
    }
}
