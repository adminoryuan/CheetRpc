package com.cheet.example.server;

/**
 * @author yh
 * @date 2022/6/21 上午8:40
 */

public class RpcImpl{


    public int GetRandom(int a,int b){
      return   a-b;
    }

    public int Add(int a, int b) {
        return a+b;
    }
}
