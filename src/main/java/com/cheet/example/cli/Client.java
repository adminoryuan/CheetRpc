package com.cheet.example.cli;

import com.cheet.api.impl.CheetRpcClientImpl;
import com.cheet.example.server.RpcImpl;

/**
 * @author yh
 * @date 2022/6/21 上午8:42
 */
public class Client {
    public static void main(String[] args) throws Exception {

        CheetRpcClientImpl client=new CheetRpcClientImpl();
        client.Connect("127.0.0.1",8080);
        Object call = client.Call(".RpcImpl","GetRandom", 1, 2);

        System.out.println(call.toString());
        call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());

    }
}
