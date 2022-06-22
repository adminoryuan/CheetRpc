package com.cheet.api;

/**
 * @author yh
 * @date 2022/6/20 下午7:53
 */
public interface CheetRpcServer {

    /**
     * 穿入一个rpc 基类
     * @param cls
     */
    void Rigist(Class cls);

    void ListenServer(int port);
}
