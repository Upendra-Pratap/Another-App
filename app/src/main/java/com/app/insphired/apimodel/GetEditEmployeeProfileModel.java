package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetEditEmployeeProfileModel {

    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public GetEditEmployeeProfileData data;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public GetEditEmployeeProfileData getData() {
        return data;
    }
}
