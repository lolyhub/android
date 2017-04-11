package com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos;

public class RegistrationRequest
{
    private String first_name;

    private String email;

    private String last_name;

    private String password;

    private String newsLetter;

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getNewsLetter ()
    {
        return newsLetter;
    }

    public void setNewsLetter (String newsLetter)
    {
        this.newsLetter = newsLetter;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [first_name = "+first_name+", email = "+email+", last_name = "+last_name+", password = "+password+", newsLetter = "+newsLetter+"]";
    }
}

			