package com.cheet.Entity;

import com.cheet.discovery.AbstLoadbalancing;
import lombok.Builder;
import lombok.Data;

/**
 * @author yh
 * @date 2022/6/27 下午9:09
 */
@Data
@Builder
public class ZookeeperConfig {

    private String zkAddr;
    private String ServerNode;
    private String UserName;
    private String Password;
    private AbstLoadbalancing load;
}
