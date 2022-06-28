package com.cheet.discovery.impl.cing;

import com.cheet.discovery.AbstLoadbalancing;
import com.cheet.netty.client.NettyClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yh
 * @date 2022/6/28 下午2:01
 * 随机轮询算法
 */
public class Randombalancing extends AbstLoadbalancing {

    private Random random=new Random();
    @Override
    public NettyClient GetVistRpcNode() {

        int i = random.nextInt(list.size());

        return list.get(i);
    }

    @Override
    public void AddRpcNode(NettyClient client) {
        list.add(client);
    }
}
