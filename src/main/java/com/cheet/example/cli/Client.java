package com.cheet.example.cli;

import com.cheet.Entity.ZookeeperConfig;
import com.cheet.api.impl.CheetRpcClientImpl;
import com.cheet.discovery.impl.StandaloneDiscovery;
import com.cheet.discovery.impl.ZookeeperDisovery;
import com.cheet.discovery.impl.cing.Pollingbalancing;
import com.cheet.discovery.impl.cing.Randombalancing;
import com.cheet.example.server.RpcImpl;

/**
 * @author yh
 * @date 2022/6/21 上午8:42
 */
public class Client {
    public static void main(String[] args) throws Exception {

        CheetRpcClientImpl client=new CheetRpcClientImpl();

        //load () 选择负载均衡算法
        ZookeeperConfig rpc_zk = ZookeeperConfig.builder().load(new Pollingbalancing())
                .ServerNode("/rpc")
                .zkAddr("127.0.0.1:9000").build();

        client.SetDiscovery(new ZookeeperDisovery(rpc_zk));


        Object call = client.Call(".RpcImpl","GetRandom", 1, 2);

        System.out.println(call.toString());

        call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());


        call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());



    }
}
