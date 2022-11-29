package com.cheet.example.cli;

/**
 * @author yh
 * @date 2022/6/22 下午7:26
 */
public class RpcFunc {

    public static interface  RpcImpl{
        public int GetRandom(int a,int b);

        public int Add(int a, int b);
    }
}
