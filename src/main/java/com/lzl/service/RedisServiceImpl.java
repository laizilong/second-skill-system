package com.lzl.service;

import com.lzl.util.RedisUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RedisServiceImpl implements RedisService {
  @Autowired private RedisUtil redisUtil;

//  public String seckill(String userId, String goodsId) {
//
//    return "";
//  }

  public String seckill(String userId, String goodsId) {
    SessionCallback sessionCallback =
        new SessionCallback() {
          @Override
          public Object execute(RedisOperations redisOperations) throws DataAccessException {
            redisOperations.watch(goodsId + "_count");
            Object stockCnt = redisUtil.get(goodsId + "_count");
            if (stockCnt == null || (int) stockCnt <= 0) {
              return false;
            }
            redisOperations.multi();
            redisUtil.decr(goodsId + "_count");
            // 用户购买个数 拼装规则
            redisUtil.set(goodsId + "_" + userId, 1);
            return redisOperations.exec();
          }
        };
    redisUtil.execute(sessionCallback);
    if (redisUtil.hasKye(goodsId + "_" + userId)) {
      return userId + "秒杀成功";
    }
    return userId + "秒杀失败";
  }

  public String seckill1(String userId, String goodsId) {
    //        redisUtil.set("k_"+userId,userId+"_"+goodsId);

    Date startTime = (Date) redisUtil.get(goodsId + "_startTime");
    System.out.println(startTime);
    if (startTime == null || new Date().before(startTime)) {
      return "秒杀还未开始";
    }
    int stockCnt = (int) redisUtil.get(goodsId + "_count");
    if (stockCnt <= 0) {
      return "商品秒杀一空";
    }
    if (redisUtil.get(goodsId + "_" + userId) != null) {
      return "用户已秒杀过";
    }
    redisUtil.decr(goodsId + "_count");
    // 用户购买个数 拼装规则
    redisUtil.set(goodsId + "_" + userId, 1);
    return userId + "用户秒杀成功";
  }

  @Override
  public boolean initData(String goodsId, int stockCnt) {
    redisUtil.set(goodsId + "_count", stockCnt);
    try {
      String string = "2021-10-2 21:00:00";
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = format.parse(string);
      redisUtil.set(goodsId + "_startTime", date);
    } catch (Exception exception) {

    }
    return false;
  }
}
