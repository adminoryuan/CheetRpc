package com.cheet.discovery.impl;

import com.cheet.Entity.CheetAddr;
import com.cheet.Entity.ZookeeperConfig;
import com.cheet.discovery.AbstLoadbalancing;
import com.cheet.discovery.ServerDiscovery;
import com.cheet.netty.client.NettyClient;
import lombok.SneakyThrows;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yh
 * @date 2022/6/27 下午8:19
 * 集群注册中心
 */
public class ZookeeperDisovery implements ServerDiscovery {

    private static ZookeeperConfig zkConfig;

    private AbstLoadbalancing roud; //负载均衡


    private ZooKeeper zooKeeper;
    public ZookeeperDisovery(ZookeeperConfig zkConfig) {
        zkConfig=zkConfig;
        roud=zkConfig.getLoad();

        InitRoud(zkConfig);

    }

    /**
     * 监听zk node 断开等情况
     * @throws InterruptedException
     * @throws KeeperException
     */
    private  void MonitorZkNode() throws InterruptedException, KeeperException {
        while (true) {
           final CountDownLatch downLatch = new CountDownLatch(1);

            zooKeeper.getChildren(zkConfig.getServerNode(), new Watcher() {
                @SneakyThrows
                @Override
                public void process(WatchedEvent watchedEvent) {

                    if (watchedEvent.getType() != null) {
                        roud.NodeChange();
                        List<String> children = zooKeeper.getChildren(zkConfig.getServerNode(), true);
                        System.out.println("当前节点"+children.size());
                        for (String child : children) {

                            Stat stat=new Stat();


                            byte[] data = zooKeeper.getData(zkConfig.getServerNode()+"/"+child, false, stat);

                            roud.AddRpcNode(getNettyClient(data));


                        }

                        roud.NodeChangeBegin();

                        downLatch.countDown();

                    }
                }
            });

            synchronized(downLatch) {

                downLatch.await();

            }

        }
    }

    public NettyClient getNettyClient(byte[] data){
        NettyClient nettyClient = new NettyClient();

        String addr=new String(data);


        String[] split = addr.split(":");

        nettyClient.Dial(split[0],Integer.valueOf(split[1]));

        return nettyClient;
    }
    public void InitRoud(ZookeeperConfig config){
        //从zk中读取注册的rpc 服务
        try {
            zooKeeper = new ZooKeeper(config.getZkAddr(), 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });

            List<String>  list=zooKeeper.getChildren(config.getServerNode(),true);

            System.out.println(list.size());
            for (String s : list) {

                Stat stat=new Stat();

                byte[] data = zooKeeper.getData(config.getServerNode()+"/"+s, false, stat);

                roud.AddRpcNode(getNettyClient(data));

            }

        } catch (IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //监听
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MonitorZkNode();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public NettyClient getNettyClient() {
         return  roud.GetVistRpcNode();
    }
}
