package com.tiennb.btl.model;

public class Word {
    protected String ta;
    protected String tv;
    protected String cachDoc;
    protected int flag;
    protected String ImagePath;

    @Override
    public String toString() {
        return "ta: "+ta+" tv: "+tv+" cachDoc: "+cachDoc+" image path: "+ImagePath;
    }

    public Word() {
    }

    public Word(String ta, String tv, String cachDoc) {
        this.ta = ta;
        this.tv = tv;
        this.cachDoc = cachDoc;
        flag = 0;
    }

    public Word(String ta, String tv, String cachDoc, String ImagePath) {
        this.ta = ta;
        this.tv = tv;
        this.cachDoc = cachDoc;
        this.ImagePath = ImagePath;
        flag = 0;
    }
    
    

    public String getTa() {
        return ta;
    }

    public void setTa(String a) {
        this.ta = a;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getCachDoc() {
        return cachDoc;
    }

    public void setCachDoc(String cachDoc) {
        this.cachDoc = cachDoc;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
    
    
}
