package com.lzl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
  @Autowired private RedisTemplate<String, Object> redisTemplate;

  public void set(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }
  // 增加过期时间和过期时间的单位
  public void set(String key, Object value, long timeout) {
    redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
  }

  public Object get(String key) {
    if (redisTemplate.hasKey(key)) {
      return redisTemplate.opsForValue().get(key);
    }
    return null;
  }

  public boolean delete(String key) {
    redisTemplate.delete(key);
    if (redisTemplate.hasKey(key)) {
      return false;
    }
    return true;
  }

  public void decr(String key) {
    redisTemplate.opsForValue().decrement(key);
  }
  public Object execute(SessionCallback sessionCallback){
   return redisTemplate.execute(sessionCallback);
  }
  public boolean  hasKye(String key){
    return redisTemplate.hasKey(key);
  }
}
