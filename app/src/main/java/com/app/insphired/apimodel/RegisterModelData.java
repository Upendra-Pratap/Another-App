package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterModelData {

    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("Mobile")
    @Expose
    public String mobile;
    @SerializedName("intro_video")
    @Expose
    public String introVideo;
    @SerializedName("identity")
    @Expose
    public String identity;
    @SerializedName("device_token")
    @Expose
    public String deviceToken;
    @SerializedName("login_type")
    @Expose
    public String loginType;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("is_corporator")
    @Expose
    public Integer isCorporator;

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getIntroVideo() {
        return introVideo;
    }

    public String getIdentity() {
        return identity;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public String getLoginType() {
        return loginType;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public Integer getIsCorporator() {
        return isCorporator;
    }



}

