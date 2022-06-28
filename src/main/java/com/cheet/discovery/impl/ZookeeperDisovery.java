package com.cheet.discovery.impl;

import com.cheet.Entity.CheetAddr;
import com.cheet.Entity.ZookeeperConfig;
import com.cheet.discovery.AbstLoadbalancing;
import com.cheet.discovery.ServerDiscovery;
import com.cheet.netty.client.NettyClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * @author yh
 * @date 2022/6/27 下午8:19
 */
public class ZookeeperDisovery implements ServerDiscovery {

    private static ZookeeperConfig zkServerAddr;

    private AbstLoadbalancing roud;

    public ZookeeperDisovery(ZookeeperConfig zkConfig) {
        zkServerAddr=zkConfig;
        roud=zkConfig.getLoad();

        InitRoud(zkConfig);

    }

    public void InitRoud(ZookeeperConfig config){

        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(config.getZkAddr(), 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });

            List<String>  list=zooKeeper.getChildren(config.getServerNode(),true);

            System.out.println(list.size());
            for (String s : list) {

                NettyClient nettyClient = new NettyClient();

                Stat stat=new Stat();

                byte[] data = zooKeeper.getData(config.getServerNode()+"/"+s, false, stat);

                String addr=new String(data);

                System.out.println(addr);

                String[] split = addr.split(":");
                nettyClient.Dial(split[0],Integer.valueOf(split[1]));

                roud.AddRpcNode(nettyClient);
            }

        } catch (IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //监听

    }

    @Override
    public NettyClient getNettyClient() {
         return  roud.GetVistRpcNode();
    }
}
