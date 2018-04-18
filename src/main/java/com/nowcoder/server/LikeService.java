package com.nowcoder.server;

import com.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    JedisAdapter jedisAdapter;

    public long like(int userId,int entityType,int entityId) {
        String key = RedisKeyUtil.getLikeKey(entityType,entityId);
        jedisAdapter.sadd(key,String.valueOf(userId));

        String disKey = RedisKeyUtil.getDisLikeKey(entityType,entityId);
        jedisAdapter.srem(disKey,String.valueOf(userId));

        return jedisAdapter.scard(key);
    }

    public long dislike(int userId,int entityType,int entityId) {
        String key = RedisKeyUtil.getLikeKey(entityType,entityId);
        jedisAdapter.srem(key,String.valueOf(userId));

        String disKey = RedisKeyUtil.getDisLikeKey(entityType,entityId);
        jedisAdapter.sadd(disKey,String.valueOf(userId));

        return jedisAdapter.scard(key);
    }
}
