package com.taotao.rest.service;

/**
 * Created by ${chenlunwei} on 2017/7/4.
 */
public interface JedisClient {

    String get(String key);

    String set(String key,String value);

    String hget(String hkey,String key);

    Long hset(String hkey,String key, String value);

    Long del(String key);

    Long hdel(String hkey,String key);

    Long incr(String key);

    Long decr(String key);

    //设置过期时间
    Long expire(String key, int second);

    Long ttl(String key);

    byte[] get(byte[] key);

    String setex(byte[] key,Integer expireTime,byte[] value);
}
