package com.cheet.example.server;

import com.cheet.Entity.RegZkConfig;
import com.cheet.core.api.CheetRpcServer;
import com.cheet.core.api.impl.CheetRpcServerImpl;

/**
 * @author yh
 * @date 2022/6/21 上午8:39
 */

public class Server {
    public static void main(String[] args) {
        CheetRpcServer ser=new CheetRpcServerImpl();
        ser.Rigist(RpcImpl.class); //注册rpc 函数
        RegZkConfig rpcTest = RegZkConfig.builder()
                .RpcNode("/rpc2")
                .CurrNodeName("E")
                .zkAddr("127.0.0.1:9000")
                .Curraddr("127.0.0.1:9991").build();

        //将自己注册到注册中心中
        ser.AddZkdiscovery(rpcTest);

        //设置心跳
        ser.Heartbeat(5000,10,5000);

        ser.ListenServer(9991);
    }
}
