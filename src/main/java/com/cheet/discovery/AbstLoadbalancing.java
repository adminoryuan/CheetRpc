package com.cheet.discovery;

import com.cheet.api.CheetRpcClient;
import com.cheet.netty.client.NettyClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yh
 * @date 2022/6/27 下午9:29
 * 负载均衡算法
 */
public abstract class AbstLoadbalancing {
   protected List<NettyClient> list=new ArrayList<>();

    //获取当前访问的rpc节点
   public abstract NettyClient GetVistRpcNode();

   public abstract void AddRpcNode(NettyClient client);

}
