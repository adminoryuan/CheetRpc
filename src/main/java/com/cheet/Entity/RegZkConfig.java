package com.cheet.Entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author yh
 * @date 2022/6/28 下午3:53
 */
@Data
@Builder
public class RegZkConfig {
    String zkAddr;

    String RpcNode;

    String CurrNodeName;
    String Curraddr;

}
