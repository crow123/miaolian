package com.miaolian.cn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangyj on 2017/8/10.
 *
 */
@Component
@ConfigurationProperties(prefix="miaolian")
public class YmlConfig {

    //String类型的一定需要setter来接收属性值；maps, collections, 和 arrays 不需要
    private Map<String,String> server = new HashMap<>();

    private Map<String,String> datasource = new HashMap<>();

    private Map<String,String> jedis = new HashMap<>();

    public Map<String, String> getServer() {
        return server;
    }

    public Map<String, String> getDatasource() {
        return datasource;
    }

    public Map<String, String> getJedis() {
        return jedis;
    }
}
