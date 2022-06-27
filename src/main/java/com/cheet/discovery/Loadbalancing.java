package com.cheet.discovery;

import com.cheet.netty.client.NettyClient;

/**
 * @author yh
 * @date 2022/6/27 下午9:29
 * 负载均衡算法
 */
public interface Loadbalancing {

    //获取当前访问的rpc节点
    public NettyClient GetVistRpcNode();
}
