package com.cheet.netty.client;

import com.cheet.Entity.RpcRequest;
import com.cheet.Entity.RpcResponse;
import com.cheet.call.OnRpcRevice;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ExecutionException;

/**
 * @author yh
 * @date 2022/6/20 下午8:32
 */
public class ClientHandle extends ChannelInboundHandlerAdapter {
    SyncFuture<RpcResponse> syncFuture=new SyncFuture<>();

    private static volatile ClientHandle handle;

    public static ClientHandle getHandleInstance(){
        if (handle==null){
            synchronized (ClientHandle.class){
                handle=new ClientHandle();
            }
        }
        return handle;
    }

    private ClientHandle(){

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

      //  syncFuture=new SyncFuture<>();
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        RpcResponse msg1 =(RpcResponse) msg;

        syncFuture.setResponse(msg1);

        super.channelRead(ctx, msg);
    }

    public Object Call() {

        return syncFuture.get().getData();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
