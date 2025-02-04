package com.app.insphired.retrofit;

import com.app.insphired.ApiModelEmployer.EmployerProfileModel;
import com.app.insphired.ApiModelEmployer.ForgotPassword;
import com.app.insphired.EmployeeActivity.BankDetailsViewModel.Bankdetails;
import com.app.insphired.EmployeeActivity.BankDetailsViewModel.GetBankdetails;
import com.app.insphired.apimodel.AcceptNotificationModel;
import com.app.insphired.apimodel.AcceptRequestModel;
import com.app.insphired.apimodel.AllEmployeeListModel;
import com.app.insphired.apimodel.BookingModel;
import com.app.insphired.apimodel.CancelEmployeeHistoryModel;
import com.app.insphired.apimodel.CancelModel;
import com.app.insphired.apimodel.ChangePasswordModel;
import com.app.insphired.apimodel.ChatCompanyModel;
import com.app.insphired.apimodel.CreateJobModel;
import com.app.insphired.apimodel.CreateSlotModel;
import com.app.insphired.apimodel.DeleteJobmodel;
import com.app.insphired.apimodel.DeleteNotificationModel;
import com.app.insphired.apimodel.DeleteTimeSlotModel;
import com.app.insphired.apimodel.EarningModel;
import com.app.insphired.apimodel.EditEmployerProfileModel;
import com.app.insphired.apimodel.EmployeeBookedListModel;
import com.app.insphired.apimodel.EmployeeEditProfileModel;
import com.app.insphired.apimodel.EmployeeHistoryModel;
import com.app.insphired.apimodel.EmployeeProfileModelFirst;
import com.app.insphired.apimodel.EmployerChatEmpModel;
import com.app.insphired.apimodel.ExternalNotificationModel;
import com.app.insphired.apimodel.Favourite_employee_model;
import com.app.insphired.apimodel.FilterModel;
import com.app.insphired.apimodel.GetBookingDetailModel;
import com.app.insphired.apimodel.GetCategoryModel;
import com.app.insphired.apimodel.GetCreateSlotsModel;
import com.app.insphired.apimodel.GetCreditModel;
import com.app.insphired.apimodel.GetEditEmployeeProfileModel;
import com.app.insphired.apimodel.GetEmployeeHistoryModel;
import com.app.insphired.apimodel.GetFavouriteModel;
import com.app.insphired.apimodel.GetProfileDetailsModel;
import com.app.insphired.apimodel.GetRequestE;
import com.app.insphired.apimodel.GetReviewModel;
import com.app.insphired.apimodel.GetWithDrawModel;
import com.app.insphired.apimodel.GiveRatingModel;
import com.app.insphired.apimodel.JobCancelModel;
import com.app.insphired.apimodel.JobListModel;
import com.app.insphired.apimodel.LocationModel;
import com.app.insphired.apimodel.LoginModel;
import com.app.insphired.apimodel.MsgEmployeeModel;
import com.app.insphired.apimodel.MyJobModel;
import com.app.insphired.apimodel.NotificationModel;
import com.app.insphired.apimodel.PaymentHistoryModel;
import com.app.insphired.apimodel.PostEmailOtpModel;
import com.app.insphired.apimodel.PostProfieEmpApi;
import com.app.insphired.apimodel.RegisterModel;
import com.app.insphired.apimodel.RejectModel;
import com.app.insphired.apimodel.RejectRequestJobModel;
import com.app.insphired.apimodel.RequestTimeSlotModel;
import com.app.insphired.apimodel.ResetPasswordModel;
import com.app.insphired.apimodel.ShowCvModel;
import com.app.insphired.apimodel.UpComingJobModel;
import com.app.insphired.apimodel.UserChatModel;
import com.app.insphired.apimodel.VerifyOtpModel;
import com.app.insphired.apimodel.WithdrawModel;
import com.app.insphired.FeedbackEmployee.EmployeeRating;
import com.app.insphired.ShowReviewApiModel.ShowEmployeeReview;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface Api {
    @Headers("Accept:application/json")
    @Multipart
    @POST("user_register")
    Call<RegisterModel>REGISTER_MODEL_CALL(
            @Part("first_name") RequestBody firstname,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("Mobile") RequestBody mobile,
            @Part MultipartBody.Part intro_ideo,
            @Part MultipartBody.Part id_pdf,
            @Part("user_type") RequestBody userType,
            @Part("device_token") RequestBody device_token,
            @Part("login_type") RequestBody login_type);

    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("user_login")
    Call<LoginModel>LOGIN_MODEL_CALL(@Field("email") String email,
                                      @Field("password") String password,
                                      @Field("device_token") String token,
                                      @Field("user_type") String userType,
                                      @Field("login_type") String login_type);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("user_change_password")
    Call<ChangePasswordModel> CHANGE_PASSWORD_MODEL_CALL(@Field("user_id") String userId,
                                                         @Field("old_password") String oldPassword,
                                                         @Field("new_password") String newPassword,
                                                         @Field("user_type") String userType);
    @Headers("Accept:application/json")
    @Multipart
    @POST("employer_update_profile")
    Call<EmployerProfileModel> EMPLOYER_PROFILE_MODEL_CALL(@Part("user_id") RequestBody user_id,
                                                           @Part MultipartBody.Part image,
                                                           @Part("company_name") RequestBody company_name,
                                                           @Part("company_email") RequestBody company_email,
                                                           @Part("address") RequestBody companyAddress,
                                                           @Part("term_condition") RequestBody term_condition);
    @Headers("Accept:application/json")
    @GET()
    Call<GetProfileDetailsModel> GET_PROFILE_DETAILS_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @Multipart
    @POST("edit_profile")
    Call<EditEmployerProfileModel> EDIT_EMPLOYER_PROFILE_MODEL_CALL(@Part("user_id") RequestBody user_id,
                                                                    @Part MultipartBody.Part image,
                                                                    @Part("user_name") RequestBody user_name,
                                                                    @Part("email") RequestBody company_email,
                                                                    @Part("mobile") RequestBody mobile,
                                                                    @Part("company_name") RequestBody companyAddress,
                                                                    @Part("location") RequestBody company_name,
                                                                    @Part("user_type") RequestBody user_type);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("save_bank_details")
    Call<Bankdetails> BANKDETAILS_CALL(@Field("user_id") String user_id,
                                       @Field("acc_holder_name") String acc_holder_name,
                                       @Field("bank_name") String bank_name,
                                       @Field("IFSC_code") String IFSC_code,
                                       @Field("acc_number") String acc_number);
    @Headers("Accept:application/json")
    @GET()
    Call<GetBankdetails> GET_BANKDETAILS_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET("all_category")
    Call<GetCategoryModel> GET_CATEGORY_MODEL_CALL();
    @Headers("Accept:application/json")
    @GET()
    Call<EmployeeProfileModelFirst> EMPLOYEE_PROFILE_MODEL_FIRST_CALL(@Url String str);


/*
    @GET()
    Call<GetProfieEmpApi>GET_PROFIE_EMP_API_CALL(@Url String str);*/
@Headers("Accept:application/json")
    @Multipart
    @POST("user_update_profile")
    Call<PostProfieEmpApi> POST_PROFIE_EMP_API_CALL(@Part("user_id") RequestBody user_id,
                                                    @Part MultipartBody.Part emp_image,
                                                    @Part("emp_id") RequestBody emp_id,
                                                    @Part("cat_id") RequestBody cat_id,
                                                    @Part("daily_rate") RequestBody daily_rate,
                                                    @Part MultipartBody.Part emp_cv,
                                                    @Part("emp_history") RequestBody emp_history,
                                                    @Part("location") RequestBody location,
                                                    @Part("term_condition") RequestBody term_condition);
    @Headers("Accept:application/json")
    @Multipart
    @POST("edit_profile")
    Call<EmployeeEditProfileModel> EMPLOYEE_EDIT_PROFILE_MODEL_CALL(@Part("user_id") RequestBody user_id,
                                                                    @Part MultipartBody.Part image,
                                                                    @Part("user_name") RequestBody user_name,
                                                                    @Part("email") RequestBody email,
                                                                    @Part("mobile") RequestBody mobile,
                                                                    @Part("daily_rate") RequestBody daily_rate,
                                                                    @Part("location") RequestBody location,
                                                                    @Part("user_type") RequestBody user_type,
                                                                    @Part("emp_id") RequestBody emp_id,
                                                                    @Part("cat_id") RequestBody cat_id,
                                                                    @Part MultipartBody.Part emp_cv,
                                                                    @Part("emp_exp") RequestBody emp_exp,
                                                                    @Part("term") RequestBody term);
    @Headers("Accept:application/json")
    @GET()
    Call<GetEditEmployeeProfileModel> GET_EDIT_EMPLOYEE_PROFILE_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("user_forgot_password")
    Call<ForgotPassword> FORGOT_PASSWORD_CALL(@Field("mobile_no") String mobile_no,
                                              @Field("user_type") String user_type);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("otp_send")
    Call<PostEmailOtpModel> POST_EMAIL_OTP_MODEL_CALL(@Field("email") String email);
    @Headers("Accept:application/json")
    @GET()
    Call<VerifyOtpModel> VERIFY_OTP_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("forgot_password_save")
    Call<ResetPasswordModel> RESET_PASSWORD_MODEL_CALL(@Field("new_password") String new_password,
                                                       @Field("confirm_password") String C_password,
                                                       @Field("user_id") String user,
                                                       @Field("user_type") String user_type);
    @Headers("Accept:application/json")
    @GET()
    Call<AllEmployeeListModel> ALL_EMPLOYEE_LIST_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("add_to_fav")
    Call<Favourite_employee_model> FAVOURITE_EMPLOYEE_MODEL_CALL(@Field("employee_id") String employee_id,
                                                                 @Field("employer_id") String employer_id,
                                                                 @Field("user_type") String user_type);
    @Headers("Accept:application/json")
    @GET()
    Call<GetFavouriteModel> GET_FAVOURITE_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("save_rating")
    Call<GiveRatingModel> GIVE_RATING_MODEL_CALL(@Field("user_id") String user_id,
                                                 @Field("employee_id") String employee_id,
                                                 @Field("rating") String rating,
                                                 @Field("comment") String comment,
                                                 @Field("user_type") String user_type);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("save_rating")
    Call<EmployeeRating> EMPLOYEE_RATING_CALL(@Field("user_id") String user_id,
                                              @Field("employer_id") String employee_id,
                                              @Field("rating") String rating,
                                              @Field("comment") String comment,
                                              @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("filter_data")
    Call<FilterModel> FILTER_MODEL_CALL(@Field("cat_id") String cat_id,
                                        @Field("location") String location,
                                        @Field("per_day") String per_day,
                                        @Field("price_range") String price_range);
    @Headers("Accept:application/json")
    @GET()
    Call<GetBookingDetailModel> GET_BOOKING_DETAIL_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("employee_book")
    Call<BookingModel>BOOKING_MODEL_CALL(@Field("employee_id") String employee_Id,
                                          @Field("user_id") String user_id,
                                          @Field("amount") String amount,
                                          @Field("time_slot_id") String time_slot_id,
                                         @Field("user_type") String user_type);
    @Headers("Accept:application/json")
    @GET()
    Call<EmployeeBookedListModel> EMPLOYEE_BOOKED_LIST_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("employee_cancel")
    Call<CancelModel> CANCEL_MODEL_CALL(@Field("id") String id);
    @Headers("Accept:application/json")
    @GET()
    Call<GetEmployeeHistoryModel> GET_EMPLOYEE_HISTORY_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<GetReviewModel> GET_REVIEW_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<ShowEmployeeReview> SHOW_EMPLOYEE_REVIEW_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<PaymentHistoryModel> PAYMENT_HISTORY_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<MyJobModel> MY_JOB_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("job_cancel")
    Call<JobCancelModel> JOB_CANCEL_MODEL_CALL(@Field("booking_id") String booking_id);
    @Headers("Accept:application/json")
    @GET()
    Call<UpComingJobModel> UP_COMING_JOB_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<ChatCompanyModel> CHAT_COMPANY_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<MsgEmployeeModel> MSG_EMPLOYEE_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("user_chat")
    Call<UserChatModel> USER_CHAT_MODEL_CALL(@Field("from_user") String from_user,
                                             @Field("to_user") String to_user,
                                             @Field("chat_message") String chat_message,
                                             @Field("user_type") String userType);
    @Headers("Accept:application/json")
    @GET()
    Call<EmployerChatEmpModel> EMPLOYER_CHAT_EMP_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("withdraw_amount")
    Call<WithdrawModel> WITHDRAW_MODEL_CALL(@Field("user_id") String user_id,
                                            @Field("amount") String amount,
                                            @Field("total_amount") String total_amount);
    @Headers("Accept:application/json")
    @GET()
    Call<GetWithDrawModel> GET_WITH_DRAW_MODEL_CALL(@Url String Str);
    @Headers("Accept:application/json")
    @GET()
    Call<GetCreditModel> GET_CREDIT_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("create_timeslot")
    Call<CreateSlotModel> CREATE_SLOT_MODEL_CALL(@Field("user_id") String user_id,
                                                 @Field("start_date") String start_date,
                                                 @Field("end_date") String end_date,
                                                 @Field("start_time") String start_time,
                                                 @Field("end_time") String end_time,
                                                 @Field("location ") String location);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("time_slot_change_request")
    Call<RequestTimeSlotModel> REQUEST_TIME_SLOT_MODEL_CALL(@Field("time_slot_id") String time_slot_id,
                                                            @Field("user_id") String user_id,
                                                            @Field("employee_id") String employee_id,
                                                            @Field("start_date") String start_date,
                                                            @Field("end_date") String end_date,
                                                            @Field("user_type") String user_type);
    @Headers("Accept:application/json")
    @GET("location")
    Call<LocationModel> LOCATION_MODEL_CALL();

    @Headers("Accept:application/json")
    @GET()
    Call<GetCreateSlotsModel> GET_CREATE_SLOTS_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<DeleteTimeSlotModel> DELETE_TIME_SLOT_MODEL_CALL(@Url String str);

    @Headers("Accept:application/json")
    @GET()
    Call<EarningModel> EARNING_MODEL_CALL(@Url String Str);

    @Headers("Accept:application/json")
    @GET()
    Call<EmployeeHistoryModel> EMPLOYEE_HISTORY_MODEL_CALL(@Url String Str);

    @Headers("Accept:application/json")
    @GET()
    Call<CancelEmployeeHistoryModel> CANCEL_EMPLOYEE_HISTORY_MODEL_CALL(@Url String Str);
    @Headers("Accept:application/json")
    @GET()
    Call<ShowCvModel> SHOW_CV_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @GET()
    Call<NotificationModel> NOTIFICATION_MODEL_CALL(@Url String str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("create_jobs")
    Call<CreateJobModel>CREATE_JOB_MODEL_CALL(@Field("employer_id") String employer_id,
                                              @Field("cat_id") String cat_id,
                                              @Field("job_title")String job_title,
                                              @Field("job_summary") String job_summary,
                                              @Field("user_type") String user_type,
                                              @Field("daily_rate") String daily_rate,
                                              @Field("start_date") String start_date,
                                              @Field("end_date") String  end_date,
                                              @Field("hourly_Time") String hourly_Time,
                                              @Field("address") String address);
    @Headers("Accept:application/json")
    @GET()
    Call<JobListModel>JOB_LIST_MODEL_CALL(@Url String Str);
    @Headers("Accept:application/json")
    @GET()
    Call<DeleteJobmodel>DELETE_JOBMODEL_CALL(@Url String Str);
    @Headers("Accept:application/json")
    @GET()
    Call<GetRequestE>GET_REQUEST_E_CALL(@Url String Str);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("accept_request")
    Call<AcceptRequestModel>ACCEPT_REQUEST_MODEL_CALL(@Field("id") String id,
                                                      @Field("user_id") String user_id,
                                                      @Field("page") String page,
                                                      @Field("user_type") String user_type);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("reject_request")
    Call<RejectRequestJobModel> REJECT_REQUEST_JOB_MODEL_CALL(@Field("id") String id,
                                                              @Field("page") String page,
                                                              @Field("user_type") String user_type);

    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("accept_request")
    Call<AcceptNotificationModel>ACCEPT_NOTIFICATION_MODEL_CALL(@Field("id") String id,
                                                                @Field("user_id") String user_id);
    @Headers("Accept:application/json")
    @FormUrlEncoded
    @POST("reject_request")
    Call<RejectModel>REJECT_MODEL_CALL(@Field("id") String id);
    @Headers("Accept:application/json")
    @GET()
    Call<ExternalNotificationModel> EXTERNAL_NOTIFICATION_MODEL_CALL(@Url String Str);
    @Headers("Accept:application/json")
    @GET()
    Call<DeleteNotificationModel> DELETE_NOTIFICATION_MODEL_CALL(@Url String Str);


}
