package com.lzl.service;

import com.lzl.mapper.SecKillGoodsMapper;
import com.lzl.model.Goods;
import com.lzl.mapper.GoodsMapper;
import com.lzl.model.SecKillGoods;
import com.lzl.vo.GoodsDetailVo;
import com.lzl.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
  @Autowired private GoodsMapper goodsMapper;

  @Autowired private SecKillGoodsMapper secKillGoodsMapper;

  @Override
  public List<GoodsVo> getGoods() {
    List<GoodsVo> result = new ArrayList<>();
    List<Goods> goodsList = goodsMapper.getGoods();
    for (Goods goods : goodsList) {
      SecKillGoods secKillGoods = secKillGoodsMapper.getSecGoodsById(goods.getGood_id());
      GoodsVo goodsVo = new GoodsVo();
      goodsVo.setGoodsId(goods.getGood_id());
      goodsVo.setName(goods.getName());
      goodsVo.setPrice(goods.getPrice());
      goodsVo.setImgPath(goods.getImg_path());
      goodsVo.setSecKillPrice(secKillGoods.getSecKills_price());
      goodsVo.setStockNum(secKillGoods.getStock_cnt());
      goodsVo.setStartTime(secKillGoods.getStart_time());
      result.add(goodsVo);
    }
    return result;
    //       return  goodsMapper.getGoods();
  }

  @Override
  public GoodsDetailVo getGoodsDetail(String goodsId) {
          Goods goods=goodsMapper.getGoodsById(goodsId);
          SecKillGoods secKillGoods=secKillGoodsMapper.getSecGoodsById(goodsId);

          GoodsDetailVo detailVo=new GoodsDetailVo();
          detailVo.setName(goods.getName());
          detailVo.setImgPath(goods.getImg_path());
          detailVo.setPrice(goods.getPrice());
          detailVo.setStockCnt(secKillGoods.getStock_cnt());
          detailVo.setSecKillPrice(secKillGoods.getSecKills_price());
          detailVo.setStartTime(secKillGoods.getStart_time());
          detailVo.setEndTime(secKillGoods.getEnd_time());
          return detailVo;
  }

  public void reduceStockCnt(String goodsId){
    SecKillGoods secKillGoods=secKillGoodsMapper.getSecGoodsById(goodsId);
    secKillGoods.setStock_cnt(secKillGoods.getStock_cnt()-1);
    secKillGoodsMapper.reduceStockCnt(secKillGoods);
  }

}
