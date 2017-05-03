package com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo;

/**
 * Created by neosoft on 3/5/17.
 */

public class CartResponse {
    private String ResultMsg;
    private String RequestType;

    public String getRequestType() {
        return RequestType;
    }

    public void setRequestType(String requestType) {
        RequestType = requestType;
    }

    public String getResultMsg ()
    {
        return ResultMsg;
    }

    public void setResultMsg (String ResultMsg)
    {
        this.ResultMsg = ResultMsg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ResultMsg = "+ResultMsg+"]";
    }
}
