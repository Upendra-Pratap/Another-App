package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectRequestJobModelData {
    @SerializedName("ids")
    @Expose
    public Integer ids;
    @SerializedName("employee_id")
    @Expose
    public String employeeId;

    public Integer getIds() {
        return ids;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getStartTimeslot() {
        return startTimeslot;
    }

    public String getEndTimeslot() {
        return endTimeslot;
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

    public String getEmployerStartDate() {
        return employerStartDate;
    }

    public String getEmployerStartTime() {
        return employerStartTime;
    }

    public String getEmployerEndDate() {
        return employerEndDate;
    }

    public String getEmployerEndTime() {
        return employerEndTime;
    }

    public String getUserType() {
        return userType;
    }

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    @SerializedName("employer_id")
    @Expose
    public String employerId;
    @SerializedName("start_timeslot")
    @Expose
    public String startTimeslot;
    @SerializedName("end_timeslot")
    @Expose
    public String endTimeslot;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("employer_start_date")
    @Expose
    public String employerStartDate;
    @SerializedName("employer_start_time")
    @Expose
    public String employerStartTime;
    @SerializedName("employer_end_date")
    @Expose
    public String employerEndDate;
    @SerializedName("employer_end_time")
    @Expose
    public String employerEndTime;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("time_slot_id")
    @Expose
    public Integer timeSlotId;
}
