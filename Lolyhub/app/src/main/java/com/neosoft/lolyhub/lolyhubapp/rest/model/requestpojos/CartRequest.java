package com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos;

/**
 * Created by neosoft on 3/5/17.
 */

public class CartRequest {
    private String userID;

    private String product_id;


    public String getUserID ()
    {
        return userID;
    }

    public void setUserID (String userID)
    {
        this.userID = userID;
    }

    public String getProduct_id ()
    {
        return product_id;
    }

    public void setProduct_id (String product_id)
    {
        this.product_id = product_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userID = "+userID+", product_id = "+product_id+"]";
    }
}
