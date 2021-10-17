package com.lzl.mapper;

import com.lzl.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    public  int addOrder(Order order);
}
