package com.nowcoder.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class JedisAdapter implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(JedisPool.class);
    JedisPool pool;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/12");
    }

    private static final String SADD = "sadd";
    private static final String SREM = "srem";
    private static final String SCARD = "scard";
    private static final String SISMEMBER = "sismember";
    private static final String LPUSH = "lpush";
    private Object operator(String type,String key,String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            switch (type) {
                case SADD :
                    return jedis.sadd(key, value);
                case SREM :
                    return jedis.srem(key, value);
                case SCARD:
                    return jedis.scard(key);
                case SISMEMBER :
                    return jedis.sismember(key,value);
                case LPUSH :
                    return jedis.lpush(key,value);
            }
        } catch (Exception e) {
            logger.error("发生异常" + type + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    private static final String BRPOP = "brpop";
    private Object operator2(String type,int timeout,String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            switch (type) {
                case BRPOP :
                    return jedis.brpop(timeout, key);
            }
        } catch (Exception e) {
            logger.error("发生异常" + type + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public long sadd(String key, String value) {
        return (long) operator(SADD,key,value);
    }

    public long srem(String key, String value) {
        return (long) operator(SREM,key,value);
    }

    public long scard(String key) {
        return (long) operator(SCARD,key,null);
    }

    public boolean sismember(String key,String value) {
        return (boolean) operator(SISMEMBER,key,value);
    }

    public List<String> brpop(int timeout, String key) {
        return (List) operator2(BRPOP,timeout,key);
    }

    public long lpush(String key,String value) {
        return (long) operator(LPUSH,key,value);
    }
}
