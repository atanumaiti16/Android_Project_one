package com.example.atanu.ecommerce_project_one.App;

public class MyPojo {

    String cId ,cName, cDescription, cimagerl;

    public MyPojo(String   cName, String cDescription, String cimagerl, String cId) {
        this.cId = cId;


        this.cName = cName;
        this.cDescription = cDescription;
        this.cimagerl = cimagerl;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getCimagerl() {
        return cimagerl;
    }

    public void setCimagerl(String cimagerl) {
        this.cimagerl = cimagerl;
    }
}
