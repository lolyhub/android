package com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo;

public class RegistrationResponse
{
    private String ResultMsg;

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
