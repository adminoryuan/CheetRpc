package com.cheet.core.api;

import com.cheet.Entity.RegZkConfig;

/**
 * @author yh
 * @date 2022/6/20 下午7:53
 */
public interface CheetRpcServer {

    /**
     * 传入一个rpc 服务类
     * @param cls
     */
    void Rigist(Class cls);

    /**
     * 配置注册中心
     * @param config
     */
    void AddZkdiscovery(RegZkConfig config);

    /**
     * 开启rpc 服务
     * @param port
     */
    void ListenServer(int port);

    /**
     * 配置心跳
     * @param WriteTime 写时间
     * @param ReadOutTime 读时间
     * @param ALL_READTIME
     */
    void Heartbeat(int WriteTime,int ReadOutTime,int ALL_READTIME);
}
