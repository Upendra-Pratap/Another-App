package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRequestEData {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("joining_date")
    @Expose
    public String joiningDate;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("cat_name")
    @Expose
    public String catName;
    @SerializedName("daily_rate")
    @Expose
    public String dailyRate;
    @SerializedName("is_corporator")
    @Expose
    public Integer isCorporator;
    @SerializedName("company_image")
    @Expose
    public String companyImage;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("company_address")
    @Expose
    public String companyAddress;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("user_type")
    @Expose
    public String userType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(String dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Integer getIsCorporator() {
        return isCorporator;
    }

    public void setIsCorporator(Integer isCorporator) {
        this.isCorporator = isCorporator;
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
