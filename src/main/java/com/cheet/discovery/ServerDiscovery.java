package com.cheet.discovery;

import com.cheet.Entity.CheetAddr;
import com.cheet.netty.client.NettyClient;

/**
 * @author yh
 * @date 2022/6/27 下午8:15
 */
public interface ServerDiscovery {

    /**
     * 获得一个与远程rpc 服务端链接的客户端
     * @return
     */
    NettyClient getNettyClient();
}
