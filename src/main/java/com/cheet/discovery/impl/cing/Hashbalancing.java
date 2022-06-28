package com.cheet.discovery.impl.cing;

import com.cheet.discovery.AbstLoadbalancing;
import com.cheet.netty.client.NettyClient;

/**
 * @author yh
 * @date 2022/6/28 下午2:04
 */
public class Hashbalancing extends AbstLoadbalancing {

    @Override
    public NettyClient GetVistRpcNode() {
        return null;
    }

    @Override
    public void AddRpcNode(NettyClient client) {
        list.add(client);
    }
}
