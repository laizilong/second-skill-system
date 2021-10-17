package com.lzl.service;

import com.lzl.vo.GoodsDetailVo;
import com.lzl.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    public List<GoodsVo> getGoods();

    public GoodsDetailVo getGoodsDetail(String goodsId);

    public void reduceStockCnt(String goodsId);
}
