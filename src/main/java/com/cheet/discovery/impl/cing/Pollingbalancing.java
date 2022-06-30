package com.cheet.discovery.impl.cing;

import com.cheet.discovery.AbstLoadbalancing;
import com.cheet.netty.client.NettyClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yh
 * @date 2022/6/28 下午2:12
 */
public class Pollingbalancing extends AbstLoadbalancing {

    volatile int index=0;




    @Override
    public NettyClient GetVistRpcNode() {
        writeLock.readLock().lock();

        NettyClient client=list.get(index++);

        if (index>=list.size()){
            index=0;
        }
        writeLock.readLock().unlock();
        return client;
    }


    @Override
    public void NodeChangeBegin() {
        index=0;
        super.NodeChangeBegin();
    }

    @Override
    public void AddRpcNode(NettyClient client) {

        list.add(client);

    }
}
