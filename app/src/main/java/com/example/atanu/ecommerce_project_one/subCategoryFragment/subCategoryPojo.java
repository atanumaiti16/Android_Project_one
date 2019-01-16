package com.example.atanu.ecommerce_project_one.subCategoryFragment;

public class subCategoryPojo {

    String subCategoryItemId, subCategoryItemName,  subCategoryItemDescription,SubCategoryItemImageUrl;

    public subCategoryPojo(String subCategoryItemId, String subCategoryItemName,
                           String subCategoryItemDescription, String subCategoryItemImageUrl) {
        this.subCategoryItemId = subCategoryItemId;
        this.subCategoryItemName = subCategoryItemName;
        this.subCategoryItemDescription = subCategoryItemDescription;
        this.SubCategoryItemImageUrl = subCategoryItemImageUrl;
    }

    public String getSubCategoryItemId() {
        return subCategoryItemId;
    }

    public void setSubCategoryItemId(String subCategoryItemId) {
        this.subCategoryItemId = subCategoryItemId;
    }

    public String getSubCategoryItemName() {
        return subCategoryItemName;
    }

    public void setSubCategoryItemName(String subCategoryItemName) {
        this.subCategoryItemName = subCategoryItemName;
    }

    public String getSubCategoryItemDescription() {
        return subCategoryItemDescription;
    }

    public void setSubCategoryItemDescription(String subCategoryItemDescription) {
        this.subCategoryItemDescription = subCategoryItemDescription;
    }

    public String getSubCategoryItemImageUrl() {
        return SubCategoryItemImageUrl;
    }

    public void setSubCategoryItemImageUrl(String subCategoryItemImageUrl) {
        SubCategoryItemImageUrl = subCategoryItemImageUrl;
    }
}
