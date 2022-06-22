package com.cheet.call;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yh
 * @date 2022/6/22 下午4:36
 * 推送消息
 */
public class OnRpcRevice {

   static class Node{
        Object lock;
        Object data;

        public Node(Object lock) {
            this.lock = lock;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Object getLock() {
            return lock;
        }

        public Object getData() {
            return data;
        }
    }

   Map<String,Node> objectMap=new ConcurrentHashMap<>();

   private OnRpcRevice(){}

   volatile static OnRpcRevice onRpcRevice;

   public static OnRpcRevice getIntstance(){
        if (onRpcRevice==null){
            synchronized (OnRpcRevice.class){
                onRpcRevice=new OnRpcRevice();
            }
        }
        return onRpcRevice;
    }

   public void Put(String ReqId,Object data){


       objectMap.get(ReqId).setData(data);


       Node node = objectMap.get(ReqId);
       synchronized (node.lock) {
           node.lock.notify();

       }
   }

   public Object Recv(String reqId) {

      Object lock=new Object();
       objectMap.put(reqId,new Node(lock));



       synchronized (lock) {
           try {;
               lock.wait();

           } catch (InterruptedException e) {
               e.printStackTrace();
               return null;
           }

           return objectMap.get(reqId).data;
       }
    }
}
