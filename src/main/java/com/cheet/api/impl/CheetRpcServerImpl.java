package com.cheet.api.impl;

import com.cheet.api.CheetRpcServer;
import com.cheet.call.RegistFunc;
import com.cheet.netty.NettyServer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yh
 * @date 2022/6/20 下午7:57
 */
public class CheetRpcServerImpl implements CheetRpcServer {


    private NettyServer server;


    RegistFunc func=RegistFunc.getInstance();

    public CheetRpcServerImpl(){
        server=new NettyServer();

    }




    @Override
    public void Rigist(Class cls) {

        func.Regist(cls);
    }

    @Override
    public void ListenServer(int port) {
        server.Run(port);
    }
}
