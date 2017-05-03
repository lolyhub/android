package com.neosoft.lolyhub.lolyhubapp.rest.model.requestpojos;

public class UpdateCartRequest
{
    private String userID;

    private String product_id;

    private String quantity;

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

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userID = "+userID+", product_id = "+product_id+", quantity = "+quantity+"]";
    }
}