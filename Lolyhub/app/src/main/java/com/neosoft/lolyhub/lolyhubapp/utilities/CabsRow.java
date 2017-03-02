package com.neosoft.lolyhub.lolyhubapp.utilities;

/**
 * Created by yogeshm on 2/15/2017.
 */
public class CabsRow {

    private String id;
    private Integer eta;
    private String distance;
    private String displayName;
    private String category;
    private Integer base_fair;
    private Integer minimum_fair;
    private Integer cost_per_KM;


    public CabsRow(String id,Integer eta, String distance, String displayName, String category, String amountMin, String amountMax, String surge) {
        this.id = id;
        this.eta = eta;
        this.distance = distance;
        this.displayName = displayName;
        this.category = category;
    }

    public CabsRow() {

    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public String getId() {
        return id;
    }

    public Integer getBase_fair() {
        return base_fair;
    }

    public void setBase_fair(Integer base_fair) {
        this.base_fair = base_fair;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMinimum_fair() {
        return minimum_fair;
    }

    public void setMinimum_fair(Integer minimum_fair) {
        this.minimum_fair = minimum_fair;
    }

    public Integer getCost_per_KM() {
        return cost_per_KM;
    }

    public void setCost_per_KM(Integer cost_per_KM) {
        this.cost_per_KM = cost_per_KM;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
