package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModelData {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("id_number")
    @Expose
    public Object idNumber;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("emp_image")
    @Expose
    public Object empImage;
    @SerializedName("Mobile")
    @Expose
    public String mobile;
    @SerializedName("reference_check")
    @Expose
    public Object referenceCheck;
    @SerializedName("daily_rate")
    @Expose
    public Object dailyRate;
    @SerializedName("emp_cv")
    @Expose
    public Object empCv;
    @SerializedName("crim_check")
    @Expose
    public Object crimCheck;
    @SerializedName("address")
    @Expose
    public Object address;
    @SerializedName("emp_history")
    @Expose
    public Object empHistory;
    @SerializedName("reject_reason")
    @Expose
    public Object rejectReason;
    @SerializedName("emp_skills")
    @Expose
    public Object empSkills;
    @SerializedName("term_condition")
    @Expose
    public Object termCondition;
    @SerializedName("is_duty")
    @Expose
    public Integer isDuty;
    @SerializedName("intro_video")
    @Expose
    public String introVideo;
    @SerializedName("submit_id")
    @Expose
    public Object submitId;
    @SerializedName("identity")
    @Expose
    public String identity;
    @SerializedName("verification")
    @Expose
    public Object verification;
    @SerializedName("saacp_number")
    @Expose
    public Object saacpNumber;
    @SerializedName("acc_holder_name")
    @Expose
    public Object accHolderName;
    @SerializedName("bank_name")
    @Expose
    public Object bankName;
    @SerializedName("IFSC_code")
    @Expose
    public Object iFSCCode;
    @SerializedName("acc_number")
    @Expose
    public Object accNumber;
    @SerializedName("device_token")
    @Expose
    public String deviceToken;
    @SerializedName("login_type")
    @Expose
    public Object loginType;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("is_corporator")
    @Expose
    public Integer isCorporator;
    @SerializedName("user_type")
    @Expose
    public String userType;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Object getIdNumber() {
        return idNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Object getEmpImage() {
        return empImage;
    }

    public String getMobile() {
        return mobile;
    }

    public Object getReferenceCheck() {
        return referenceCheck;
    }

    public Object getDailyRate() {
        return dailyRate;
    }

    public Object getEmpCv() {
        return empCv;
    }

    public Object getCrimCheck() {
        return crimCheck;
    }

    public Object getAddress() {
        return address;
    }

    public Object getEmpHistory() {
        return empHistory;
    }

    public Object getRejectReason() {
        return rejectReason;
    }

    public Object getEmpSkills() {
        return empSkills;
    }

    public Object getTermCondition() {
        return termCondition;
    }

    public Integer getIsDuty() {
        return isDuty;
    }

    public String getIntroVideo() {
        return introVideo;
    }

    public Object getSubmitId() {
        return submitId;
    }

    public String getIdentity() {
        return identity;
    }

    public Object getVerification() {
        return verification;
    }

    public Object getSaacpNumber() {
        return saacpNumber;
    }

    public Object getAccHolderName() {
        return accHolderName;
    }

    public Object getBankName() {
        return bankName;
    }

    public Object getiFSCCode() {
        return iFSCCode;
    }

    public Object getAccNumber() {
        return accNumber;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public Object getLoginType() {
        return loginType;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Integer getIsCorporator() {
        return isCorporator;
    }

    public String getUserType() {
        return userType;
    }
}
