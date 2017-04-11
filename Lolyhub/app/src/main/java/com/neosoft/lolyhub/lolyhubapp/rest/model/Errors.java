package com.neosoft.lolyhub.lolyhubapp.rest.model;

/**
 * Created by neosoft on 11/4/17.
 */

public class Errors {
    String msg;

    public Errors(String errorMsg){
        msg=errorMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
