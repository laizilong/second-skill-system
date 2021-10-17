package com.lzl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data@NoArgsConstructor@AllArgsConstructor
public class SecKillGoods {
    private long id;
    private String goods_id;
    private double secKills_price;
    private int stock_cnt;
    private Date start_time;
    private Date end_time;
}
