package com.lzl.service;

public interface RedisService {

    String seckill(String userId,String goodsId);

    boolean initData(String goodsId,int stockCnt);
}
