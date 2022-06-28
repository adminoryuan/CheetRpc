package com.cheet.discovery.impl.cing;

import com.cheet.discovery.AbstLoadbalancing;
import com.cheet.netty.client.NettyClient;

/**
 * @author yh
 * @date 2022/6/28 下午2:12
 */
public class Pollingbalancing extends AbstLoadbalancing {

    volatile int index=0;
    @Override
    public NettyClient GetVistRpcNode() {

        System.out.println("size"+list.size());
        NettyClient client=list.get(index++);
        if (index==list.size()){
            index=0;
        }
        return client;
    }

    @Override
    public void AddRpcNode(NettyClient client) {
        list.add(client);
    }
}
