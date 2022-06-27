package com.cheet.Entity;

import lombok.Builder;

/**
 * @author yh
 * @date 2022/6/27 下午9:09
 */
@Builder
public class ZookeeperConfig {

    private String zkAddr;
    private String ServerNode;
    private String UserName;
    private String Password;
}
