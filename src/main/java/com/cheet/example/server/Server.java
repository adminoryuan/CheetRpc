package com.cheet.example.server;

import com.cheet.api.CheetRpcClient;
import com.cheet.api.CheetRpcServer;
import com.cheet.api.ScanRpcfunc;
import com.cheet.api.impl.CheetRpcClientImpl;
import com.cheet.api.impl.CheetRpcServerImpl;

/**
 * @author yh
 * @date 2022/6/21 上午8:39
 */

public class Server {
    public static void main(String[] args) {
        CheetRpcServer ser=new CheetRpcServerImpl();
        ser.Rigist(RpcImpl.class);

        ser.ListenServer(8080);
    }
}
