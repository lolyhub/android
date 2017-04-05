package com.neosoft.lolyhub.lolyhubapp.rest.model;

import java.io.Serializable;
import java.util.List;

public class Countries implements Serializable
{
    private RestResponse RestResponse;

    public RestResponse getRestResponse ()
    {
        return RestResponse;
    }

    public void setRestResponse (RestResponse RestResponse)
    {
        this.RestResponse = RestResponse;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RestResponse = "+RestResponse+"]";
    }



    public  class RestResponse
    {
        private List<Result> result;

        private String[] messages;

        public List<Result> getResult ()
        {
            return result;
        }

        public void setResult (List<Result> result)
        {
            this.result = result;
        }

        public String[] getMessages ()
        {
            return messages;
        }

        public void setMessages (String[] messages)
        {
            this.messages = messages;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [result = "+result+", messages = "+messages+"]";
        }
    }

}
