package com.neosoft.lolyhub.lolyhubapp.rest.model;

/**
 * Created by neosoft on 28/2/17.
 */

public class User {
    String name;
    String rirname;

    public User(String name, String rirname) {
        this.name = name;
        this.rirname = rirname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRirname() {
        return rirname;
    }

    public void setRirname(String rirname) {
        this.rirname = rirname;
    }
}
