package com.lzl.enums;

import lombok.Getter;

@Getter
public enum  ErrorMsg {
    UNSTART("10001","秒杀未开始"),
    CLEARED("10002","库存已售罄");

    ErrorMsg(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
    private String code;
    private String msg;
}
