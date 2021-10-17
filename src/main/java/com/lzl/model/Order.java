package com.lzl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class Order {
    private long id;
    private String order_id;
    private String user_id;
    private String goods_id;
    private String telephone;
    private String address;
}
