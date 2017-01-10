package com.neosoft.onesignal.idealrestformat.rest.model.responsepojo;

import java.util.List;

public class Example {

private Glossary glossary;

public Glossary getGlossary() {
return glossary;
}

public void setGlossary(Glossary glossary) {
this.glossary = glossary;
}


    public class Glossary {

        private String title;
        private GlossDiv glossDiv;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public GlossDiv getGlossDiv() {
            return glossDiv;
        }

        public void setGlossDiv(GlossDiv glossDiv) {
            this.glossDiv = glossDiv;
        }



    }

}