package com.neosoft.onesignal.idealrestformat.rest.model.responsepojo;

import java.util.List;

public class GlossDef {

private String para;
private List<String> glossSeeAlso = null;

public String getPara() {
return para;
}

public void setPara(String para) {
this.para = para;
}

public List<String> getGlossSeeAlso() {
return glossSeeAlso;
}

public void setGlossSeeAlso(List<String> glossSeeAlso) {
this.glossSeeAlso = glossSeeAlso;
}


}