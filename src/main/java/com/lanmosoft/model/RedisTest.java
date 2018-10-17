package com.lanmosoft.model;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {
	
	JedisPool pool;
	
	Jedis jedis;
	
	@Before
    public void start() {

        // 初始化Redis连接池
        pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
        // 从Redis连接池中获取一个连接
        jedis = pool.getResource();
        // Redis的密码，对应redis.windows.conf中的masterauth
        jedis.auth("123456");

    }

	/**
     * 添加测试
     */
    @Test
    public void putTest() {

        jedis.set("user", "YoriChan");
        System.out.println(jedis.get("user"));

        // 输出结果：YoriChan

    }
	
}
