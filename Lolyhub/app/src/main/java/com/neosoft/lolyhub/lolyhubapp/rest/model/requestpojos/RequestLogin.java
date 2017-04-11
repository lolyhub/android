package com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos;

public class RequestLogin
{
    private String userEmail;

    private String password;

    public RequestLogin(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public String getUserEmail ()
    {
        return userEmail;
    }

    public void setUserEmail (String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userEmail = "+userEmail+", password = "+password+"]";
    }
}
