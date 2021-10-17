package com.lzl.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data@AllArgsConstructor@NoArgsConstructor
public class OrderDetailVo {
    private String name;
    private String imgPath;
    private Double price;
    private Date startTime;
    private String telephone;
    private String address;
}
