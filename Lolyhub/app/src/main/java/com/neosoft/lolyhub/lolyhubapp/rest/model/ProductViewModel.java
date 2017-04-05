package com.neosoft.lolyhub.lolyhubapp.rest.model;

import java.io.Serializable;

/**
 * Created by neosoft on 14/2/17.
 */

public class ProductViewModel implements Serializable
{
    private String title;

    private int imgIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgIds() {
        return imgIds;
    }

    public void setImgIds(int imgIds) {
        this.imgIds = imgIds;
    }

    @Override
    public String toString() {
        return "HorizontalViewModel{" +
                "title='" + title + '\'' +
                ", imgIds=" + imgIds +
                '}';
    }
}