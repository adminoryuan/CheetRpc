- # CheetRpc
- 基于netty实现的一个简易rpc 框架，会陆续完成 主要是用来学

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

- # 客户端
```java

public class Client {
    public static void main(String[] args) throws Exception {

        CheetRpcClientImpl client=new CheetRpcClientImpl();
        client.Connect("127.0.0.1",8080);
        //-1
        Object call = client.Call(".RpcImpl","GetRandom", 1, 2);

        System.out.println(call.toString());
        call = client.Call(".RpcImpl","Add", 1, 2);

        System.out.println(call.toString());//3

    }
}
```