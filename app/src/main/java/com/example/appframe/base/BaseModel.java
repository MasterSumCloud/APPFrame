package com.example.appframe.base;

import java.io.Serializable;

/**
 * author: eagle
 * created on: 2019-06-24 15:00
 * description:
 */
public class BaseModel<T> implements Serializable {
    private boolean State;
    private String Msg;
    private int errcode;
    private String errmsg;
    private T result;

    public BaseModel(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }
}
