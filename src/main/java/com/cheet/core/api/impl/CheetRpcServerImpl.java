package com.cheet.core.api.impl;

import com.cheet.Entity.RegZkConfig;
import com.cheet.core.api.CheetRpcServer;
import com.cheet.core.call.RegistFunc;
import com.cheet.netty.NettyServer;
import org.apache.zookeeper.*;


import java.io.IOException;

/**
 * @author yh
 * @date 2022/6/20 下午7:57
 */
public class CheetRpcServerImpl implements CheetRpcServer {


    private NettyServer server;

    private RegZkConfig config;

    RegistFunc func=RegistFunc.getInstance();

    public CheetRpcServerImpl(){
        server=new NettyServer();
    }


    @Override
    public void Rigist(Class cls) {

        func.Regist(cls);
    }

    /**
     * 将当前节点加入zookeeper 中
     * @param config
     */
    @Override
    public void AddZkdiscovery(RegZkConfig config) {
        this.config=config;
    }

    private void ZkDisovery(){
        String zkAddr = config.getZkAddr();

        String serverNode = config.getRpcNode();

        try {
            ZooKeeper zooKeeper = new ZooKeeper(config.getZkAddr(), 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });

            if (zooKeeper.exists(serverNode,false)==null){
                System.out.println("增加主节点");
                zooKeeper.create(serverNode,config.getCurraddr().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            zooKeeper.create(serverNode+"/"+config.getCurrNodeName(),config.getCurraddr().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        } catch (IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void ListenServer(int port) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ZkDisovery();
            }
        }).start();
        server.Run(port);





    }

    @Override
    public void Heartbeat(int WriteTime, int ReadOutTime, int ALL_READTIME) {

    }
}
