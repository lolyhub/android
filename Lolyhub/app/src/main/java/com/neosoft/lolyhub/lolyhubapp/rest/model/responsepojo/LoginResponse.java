package com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo;

public class LoginResponse
{
    private String UserRole;

    private String Email;

    private String ResultMsg;

    private String UserID;

    private String UserName;

    private String password;

    public String getUserRole ()
    {
        return UserRole;
    }

    public void setUserRole (String UserRole)
    {
        this.UserRole = UserRole;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getResultMsg ()
    {
        return ResultMsg;
    }

    public void setResultMsg (String ResultMsg)
    {
        this.ResultMsg = ResultMsg;
    }

    public String getUserID ()
    {
        return UserID;
    }

    public void setUserID (String UserID)
    {
        this.UserID = UserID;
    }

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
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
        return "ClassPojo [UserRole = "+UserRole+", Email = "+Email+", ResultMsg = "+ResultMsg+", UserID = "+UserID+", UserName = "+UserName+", password = "+password+"]";
    }
}

			