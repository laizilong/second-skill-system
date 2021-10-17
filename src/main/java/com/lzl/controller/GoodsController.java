package com.lzl.controller;

import com.lzl.enums.ErrorMsg;
import com.lzl.model.Order;
import com.lzl.service.GoodsService;
import com.lzl.service.OrderService;
import com.lzl.vo.GoodsDetailVo;
import com.lzl.vo.GoodsVo;
import com.lzl.vo.OrderDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class GoodsController {
  @Autowired private GoodsService goodsService;
  @Autowired private OrderService orderService;

  @RequestMapping("/")
  public String list(Model model) {
    List<GoodsVo> goodsList = goodsService.getGoods();
    model.addAttribute("goodsList", goodsList);
    //      String value=new Gson().toJson(goodsList);
    return "list";
  }

  @RequestMapping("goodsDetail/{goodsId}")
  public String goodsDetails(Model model, @PathVariable("goodsId") String goodsId) {
    GoodsDetailVo detailVo = goodsService.getGoodsDetail(goodsId);
    model.addAttribute("detail", detailVo);
    Date startTime = detailVo.getStartTime();
    Date endTime = detailVo.getEndTime();
    Date nowTime = new Date();
    // 用0表示秒杀还未开始 1表示秒杀进行中 2表示秒杀以结束
    int status;
    int remainSeconds = -1;
    if (nowTime.before(startTime)) {
      status = 0;
      remainSeconds = (int) ((startTime.getTime() - nowTime.getTime()) / 1000);
    } else if (nowTime.after(endTime)) {
      status = 2;
    } else {
      status = 1;
    }
    model.addAttribute("status", status);
    model.addAttribute("remainSeconds", remainSeconds);
    return "detail";
  }

  @PostMapping("toSeckill")
  public String toSeckill(Model model, String goodsId) {

    GoodsDetailVo goodsDetailVo = goodsService.getGoodsDetail(goodsId);
    if (new Date().before(goodsDetailVo.getStartTime())) {
      model.addAttribute("msg", ErrorMsg.UNSTART.getMsg());
      return "fail";
    }
    if (goodsDetailVo.getStockCnt() < 1) {
      model.addAttribute("msg", ErrorMsg.CLEARED.getMsg());
    }

    // 减库存 生成订单
    goodsService.reduceStockCnt(goodsId);

    Order order = new Order();
    order.setOrder_id("123456_xxx");
    order.setUser_id("xxx");
    order.setGoods_id(goodsId);
    order.setTelephone("110");
    order.setAddress("天安门");
    orderService.addOrder(order);

    OrderDetailVo orderDetailVo=new OrderDetailVo();
    orderDetailVo.setName(goodsDetailVo.getName());
    orderDetailVo.setImgPath(goodsDetailVo.getImgPath());
    orderDetailVo.setPrice(goodsDetailVo.getPrice());
    orderDetailVo.setStartTime(new Date());
    orderDetailVo.setTelephone(order.getTelephone());
    orderDetailVo.setAddress(order.getAddress());
    model.addAttribute("orderDetailVo",orderDetailVo);

    return "orderDetail";
  }
}
