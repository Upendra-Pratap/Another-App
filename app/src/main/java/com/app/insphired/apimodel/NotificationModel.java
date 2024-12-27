package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationModel {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<NotificationModelData> data;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("count")
    @Expose
    public Integer count;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<NotificationModelData> getData() {
        return data;
    }

    public String getImage() {
        return image;
    }

    public Integer getCount() {
        return count;
    }
}
