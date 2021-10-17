package com.lzl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class Goods {
    private Long id;
    private String good_id;
    private String name;
    private String type;
    private Double price;
    private String img_path;
    private int stock_cnt;
}
