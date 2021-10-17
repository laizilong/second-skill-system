package com.lzl.service;

import com.lzl.mapper.OrderMapper;
import com.lzl.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addOrder(Order order) {

        orderMapper.addOrder(order);
    }
}
