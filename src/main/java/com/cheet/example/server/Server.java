package com.cheet.example.server;

import com.cheet.Entity.RegZkConfig;
import com.cheet.api.CheetRpcServer;
import com.cheet.api.impl.CheetRpcServerImpl;

/**
 * @author yh
 * @date 2022/6/21 上午8:39
 */

public class Server {
    public static void main(String[] args) {
        CheetRpcServer ser=new CheetRpcServerImpl();
        ser.Rigist(RpcImpl.class);
        RegZkConfig rpcTest = RegZkConfig.builder()
                .RpcNode("/rpc")
                .CurrNodeName("C")
                .zkAddr("127.0.0.1:9000")
                .Curraddr("127.0.0.1:9991").build();


        ser.AddZkdiscovery(rpcTest);

        ser.Heartbeat(1,1,1);
        ser.ListenServer(9991);
    }
}
