package com.cheet.netty.client;

/**
 * @author yh
 * @date 2022/6/23 上午10:39
 */
public class sycTest {
    public static void main(String[] args) {
        SyncFuture<Integer> syncFuture=new SyncFuture<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(syncFuture.get());
            }
        }).start();
        syncFuture.setResponse(1);


    }
}
