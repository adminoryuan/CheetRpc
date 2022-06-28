package com.cheet.netty.client;

import com.cheet.Entity.RpcResponse;
import com.sun.mail.iap.Response;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yh
 * @date 2022/6/23 上午9:48
 */
public class SyncFuture<T> implements Future<T> {

    private CountDownLatch downLatch=new CountDownLatch(1);

    private T response;

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }

    @Override
    public Throwable cause() {
        return null;
    }

    @Override
    public Future<T> addListener(GenericFutureListener<? extends Future<? super T>> genericFutureListener) {
        return null;
    }

    @Override
    public Future<T> addListeners(GenericFutureListener<? extends Future<? super T>>... genericFutureListeners) {
        return null;
    }

    @Override
    public Future<T> removeListener(GenericFutureListener<? extends Future<? super T>> genericFutureListener) {
        return null;
    }

    @Override
    public Future<T> removeListeners(GenericFutureListener<? extends Future<? super T>>... genericFutureListeners) {
        return null;
    }


    @Override
    public Future<T> sync() throws InterruptedException {
        return null;
    }

    @Override
    public Future<T> syncUninterruptibly() {
        return null;
    }

    @Override
    public Future<T> await() throws InterruptedException {
        return null;
    }

    @Override
    public Future<T> awaitUninterruptibly() {
        return null;
    }


    @Override
    public boolean await(long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean await(long l) throws InterruptedException {
        return false;
    }

    @Override
    public boolean awaitUninterruptibly(long l, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean awaitUninterruptibly(long l) {
        return false;
    }
    // 用于设置响应结果，并且做countDown操作，通知请求线程
    public void setResponse(T response) {
        this.response = response;

        downLatch.countDown();


    }
    @Override
    public T getNow() {
        return null;
    }

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public T get() {
        T result=null;
        try {

            synchronized (downLatch) {
                downLatch.await();
                result=response;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        downLatch=new CountDownLatch(1);

        return result;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
