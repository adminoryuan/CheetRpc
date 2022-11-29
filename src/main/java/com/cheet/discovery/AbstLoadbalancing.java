package com.cheet.discovery;

import com.cheet.core.api.CheetRpcClient;
import com.cheet.netty.client.NettyClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yh
 * @date 2022/6/27 下午9:29
 * 负载均衡算法
 */
public abstract class AbstLoadbalancing {

    protected ReentrantReadWriteLock writeLock=new ReentrantReadWriteLock();
    protected List<NettyClient> list=new ArrayList<>();

    protected Map<String,Integer> mapNettyIndex=new HashMap<>();
    /**
     * 当节点变更时
     * @param
     */

    public void NodeChange() {
        writeLock.writeLock().lock();
        for (NettyClient client : list) {
            client.Stop();
        }
        list=new ArrayList<>();
    }


    public void NodeChangeBegin() {
        writeLock.writeLock().unlock();
    }

    //获取当前访问的rpc节点
    public abstract NettyClient GetVistRpcNode();

   public abstract void AddRpcNode(NettyClient client);

}
