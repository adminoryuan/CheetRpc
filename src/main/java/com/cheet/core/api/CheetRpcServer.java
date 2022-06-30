package com.cheet.core.api;

import com.cheet.Entity.RegZkConfig;

/**
 * @author yh
 * @date 2022/6/20 下午7:53
 */
public interface CheetRpcServer {

    /**
     * 穿入一个rpc 服务类
     * @param cls
     */
    void Rigist(Class cls);

    void AddZkdiscovery(RegZkConfig config);

    void ListenServer(int port);

    void Heartbeat(int WriteTime,int ReadOutTime,int ALL_READTIME);
}
