package com.taotao.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * Created by ${chenlunwei} on 2017/7/4.
 */
@Service("cluster")
public class JedisClientClusterImpl implements JedisClient {
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key,value);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey,key);
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey,key,value);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public Long hdel(String hkey, String key) {
        return jedisCluster.hdel(hkey,key);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    @Override
    public Long expire(String key, int second) {
        return jedisCluster.expire(key,second);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public byte[] get(byte[] key) {
        byte[] result = jedisCluster.get(key);
        return result;
    }

    @Override
    public String setex(byte[] key, Integer expireTime, byte[] value) {
        String result = jedisCluster.setex(key,expireTime,value);
        return result;
    }
}
