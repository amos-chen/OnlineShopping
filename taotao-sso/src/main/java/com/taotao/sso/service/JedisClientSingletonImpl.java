package com.taotao.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by ${chenlunwei} on 2017/7/4.
 */
@Service("singleton")
public class JedisClientSingletonImpl implements JedisClient {

    //使用jedisPool的时候一定要colse，否则会导致无法获取资源链接redis
    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key,value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.hget(hkey,key);
        jedis.close();
        return value;
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.hset(hkey,key,value);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.hdel(hkey,key);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.decr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.expire(key,second);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        byte[] result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public String setex(byte[] key, Integer expireTime, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.setex(key,expireTime,value);
        jedis.close();
        return result;
    }
}
