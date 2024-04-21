package com.code.myweb.redis;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.TRUE;
import static org.slf4j.LoggerFactory.getLogger;


@Component
public final class RedisUtil {
    private static final Logger logger = getLogger(RedisUtil.class);
    @Autowired
    public RedisTemplate redisTemplate;
    public <T> void set(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void set(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public <T> Boolean setNX(String key, T obj) throws DataAccessException {
        final byte[] kk = serialize(key);
        final byte[] value = serialize(obj);
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.setNX(kk, value)
        );
    }

    // 仅当key不存在时才set，如果key已经存在，返回0
    public boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public long getExpire(final String key) {
        return redisTemplate.getExpire(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    public <T> T get(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }


    public boolean del(final String key) {
        return redisTemplate.delete(key);
    }


    public boolean del(final Collection collection) {
        return redisTemplate.delete(collection) > 0;
    }


    public Boolean flushDB() throws DataAccessException {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.flushDb();
            logger.info("清空 redis.");
            return TRUE;
        });
    }

    public <T> long setList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }
    public <T> List<T> getList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }


    public <T> BoundSetOperations<String, T> setSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }
    public <T> Set<T> getSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }


    public <T> void setMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }
    public <T> Map<String, T> getMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    public <T> void setHashValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    public <T> T getHashValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    public <T> List<T> getMultiHashValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    public boolean deleteHashValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * 序列化
     */
    @SuppressWarnings("unchecked")
    private <T> byte[] serialize(T obj) {
        try {
            final RedisSerializer<T> redisSerializer = (RedisSerializer<T>) redisTemplate.getValueSerializer();
            return redisSerializer.serialize(obj);
        } catch (Exception e) {
            logger.error("serialize:{}", e);
            return new byte[0];
        }
    }

    /**
     * 反序列化
     */
    @SuppressWarnings("unchecked")
    private <T> T deserialize(byte[] bytes) {
        try {
            final RedisSerializer<T> redisSerializer = (RedisSerializer<T>) redisTemplate.getValueSerializer();
            return redisSerializer.deserialize(bytes);
        } catch (Exception e) {
            logger.error("deserialize:{}", e);
            return null;
        }
    }
}
