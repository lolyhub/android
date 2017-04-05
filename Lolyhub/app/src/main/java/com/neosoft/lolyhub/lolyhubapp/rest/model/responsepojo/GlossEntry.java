package com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo;

public class GlossEntry {

private String iD;
private String sortAs;
private String glossTerm;
private String acronym;
private String abbrev;
private GlossDef glossDef;
private String glossSee;

public String getID() {
return iD;
}

public void setID(String iD) {
this.iD = iD;
}

public String getSortAs() {
return sortAs;
}

public void setSortAs(String sortAs) {
this.sortAs = sortAs;
}

public String getGlossTerm() {
return glossTerm;
}

public void setGlossTerm(String glossTerm) {
this.glossTerm = glossTerm;
}

public String getAcronym() {
return acronym;
}

public void setAcronym(String acronym) {
this.acronym = acronym;
}

public String getAbbrev() {
return abbrev;
}

public void setAbbrev(String abbrev) {
this.abbrev = abbrev;
}

public GlossDef getGlossDef() {
return glossDef;
}

public void setGlossDef(GlossDef glossDef) {
this.glossDef = glossDef;
}

public String getGlossSee() {
return glossSee;
}

public void setGlossSee(String glossSee) {
this.glossSee = glossSee;
}



}