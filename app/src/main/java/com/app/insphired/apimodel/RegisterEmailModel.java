package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterEmailModel {
    @SerializedName("email")
    @Expose
    public List<String> email;

    public List<String> getEmail() {
        return email;
    }
}
