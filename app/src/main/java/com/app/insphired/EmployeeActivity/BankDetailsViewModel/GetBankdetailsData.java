package com.app.insphired.EmployeeActivity.BankDetailsViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBankdetailsData {
    @SerializedName("acc_holder_name")
    @Expose
    public String accHolderName;
    @SerializedName("bank_name")
    @Expose
    public String bankName;
    @SerializedName("IFSC_code")
    @Expose
    public String iFSCCode;
    @SerializedName("acc_number")
    @Expose
    public String accNumber;

    public String getAccHolderName() {
        return accHolderName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getiFSCCode() {
        return iFSCCode;
    }

    public String getAccNumber() {
        return accNumber;
    }
}
