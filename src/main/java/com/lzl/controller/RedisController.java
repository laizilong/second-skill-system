package com.lzl.controller;

import com.lzl.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
  @Autowired private RedisService redisService;

  @GetMapping("/initData")
  public String initData(String goodsId, int stockCnt) {
    redisService.initData(goodsId, stockCnt);
    return "success";
  }

  @GetMapping("/seckillAPI")
  public String seckillAPI(String userId, String goodsId) {
    if (userId == null || goodsId == null) {
      return "传入的参数异常";
    }
    String result = redisService.seckill(userId, goodsId);
    System.out.println(result);
    return result;
  }
}
