package com.app.insphired.EmployeeActivity.BankDetailsViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bankdetails {
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public BankdetailsData data;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public BankdetailsData getData() {
        return data;
    }


}
