package com.cheet.Entity;

/**
 * @author yh
 * @date 2022/6/27 下午9:04
 */
public class CheetAddr {
    private String Addr;
    private int Port;

    public CheetAddr(String addr, int port) {
        Addr = addr;
        Port = port;
    }

    public String getAddr() {
        return Addr;
    }

    public int getPort() {
        return Port;
    }
}
