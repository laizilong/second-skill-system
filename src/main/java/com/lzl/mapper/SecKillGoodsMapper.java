package com.lzl.mapper;

import com.lzl.model.SecKillGoods;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SecKillGoodsMapper {
    SecKillGoods getSecGoodsById(String goods_id);
     void reduceStockCnt(SecKillGoods secKillGoods);

}
