- # CheetRpc
- 基于netty实现的一个简易rpc 框架，会陆续完成 主要是用来学习netty

- # 增加服务端心跳检测
-   超时链接将被关闭
-   ser.Heartbeat(1,1,1);

- # 使用 服务端
```java
public class RpcImpl{


    public int GetRandom(int a,int b){
      return   a-b;
    }

    public int Add(int a, int b) {
        return a+b;
    }
}

public class Server {
    public static void main(String[] args) {
        CheetRpcServer ser=new CheetRpcServerImpl();
        ser.Rigist(RpcImpl.class);

        ser.ListenServer(8080);
    }
}

```

# 客户端
- # 单机模式
```java

CheetRpcClientImpl client=new CheetRpcClientImpl();

     
        client.SetDiscovery(new StandaloneDiscovery("ip",port));


        Object call = client.Call(".RpcImpl","GetRandom", 1, 2);

        System.out.println(call.toString());

         call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());


        call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());
```

- # zookeeper 多主机模式
    - 我们可以启动多个服务端
    - 但是前提需要手动写入节点到zookeeper 中
    - 列如 /rpc_zk/127.0.0.1:8080 、/rpc_zk/127.0.0.1:8081
    - 我们分别启动8080 服务和8081 服务

    ``` java
         CheetRpcClientImpl client=new CheetRpcClientImpl();

        //load () 选择负载均衡算法
        ZookeeperConfig rpc_zk = ZookeeperConfig.builder().load(new Pollingbalancing())
                .ServerNode("/rpc_zk")
                .zkAddr("127.0.0.1:9000").build();

        client.SetDiscovery(new ZookeeperDisovery(rpc_zk));


        Object call = client.Call(".RpcImpl","GetRandom", 1, 2);

        System.out.println(call.toString());

         call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());


        call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());
    ```

