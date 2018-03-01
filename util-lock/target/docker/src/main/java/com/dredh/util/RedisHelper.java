package com.dredh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisHelper {

    private JedisPool pool;

    public RedisHelper(JedisPool pool) {
        this.pool = pool;
    }

    public Jedis getJedisInstance() {
        return pool.getResource();
    }

    public void returnResouce(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
