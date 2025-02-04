package com.app.insphired.apimodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingJobModelData {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("employer_id")
    @Expose
    public String employerId;
    @SerializedName("employee_id")
    @Expose
    public String employeeId;
    @SerializedName("start_date")
    @Expose
    public String startDate;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("detail_id")
    @Expose
    public Integer detailId;
    @SerializedName("booking_address_id")
    @Expose
    public Integer bookingAddressId;
    @SerializedName("emp_id")
    @Expose
    public Integer empId;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("emp_skills")
    @Expose
    public String empSkills;
    @SerializedName("cat_id")
    @Expose
    public Integer catId;
    @SerializedName("cat_name")
    @Expose
    public String catName;

    public Integer getId() {
        return id;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
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

    public Integer getDetailId() {
        return detailId;
    }

    public Integer getBookingAddressId() {
        return bookingAddressId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public String getImage() {
        return image;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmpSkills() {
        return empSkills;
    }

    public Integer getCatId() {
        return catId;
    }

    public String getCatName() {
        return catName;
    }
}
