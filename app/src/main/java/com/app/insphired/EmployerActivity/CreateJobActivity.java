package com.app.insphired.EmployerActivity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.insphired.apimodel.CreateJobModel;
import com.app.insphired.apimodel.GetCategoryModel;
import com.app.insphired.apimodel.GetCategoryModelData;
import com.app.insphired.R;
import com.app.insphired.databinding.ActivityCreateJobBinding;
import com.app.insphired.retrofit.Api;
import com.app.insphired.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateJobActivity extends AppCompatActivity {
    ActivityCreateJobBinding binding;
    List<String> CatList2 = new ArrayList<>();
    List<GetCategoryModelData> getCategoryModelDataList = new ArrayList<>();
    private int year, month, day;
    private String catIds, UserId, UserType;
    private String StrJTitle, StrDaily, StrHourlyTime, StrStartDate, StrEndDate, StrSummary,Straddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", MODE_PRIVATE);
        UserId = getUserIdData.getString("Id", "");
        UserType = getUserIdData.getString("userType", "");
        Log.e("feedbackkk", "change" + UserId);
        Log.e("feedbackkk", "change" + UserType);

        binding.backArrowCreateJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getCategoryApi();

        binding.jobProfileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //  Toast.makeText(getActivity(), "Country Spinner Working **********", Toast.LENGTH_SHORT).show();

                String item = binding.jobProfileSpinner.getSelectedItem().toString();
                if (item.equals(getResources().getString(R.string.JobProfile))) {
                    //CatList1.clear();

                } else {

                    catIds = String.valueOf(getCategoryModelDataList.get(i).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        binding.linearStartDateJobsProfile.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                Log.e("month", "kkk.." + month);

                //calendar.add(Calendar.MONTH, 1);

                binding.linearStartDateJobsProfile.setOnClickListener(view -> {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateJobActivity.this, R.style.MyDatePicker, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month + 1;

                            String formattedMonth = String.format("%02d", month);
                            String formatDay = String.format("%02d", day);
                            String date = year + "-" + formattedMonth + "-" + formatDay;

                            binding.startDateTxttt.setText(date);
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                });

            }
        });


        binding.linearEndDateJObsProfile.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                Log.e("month", "kkk.." + month);

                //calendar.add(Calendar.MONTH, 1);

                binding.linearEndDateJObsProfile.setOnClickListener(view -> {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateJobActivity.this, R.style.MyDatePicker, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month + 1;

                            String formattedMonth = String.format("%02d", month);
                            String formatDay = String.format("%02d", day);
                            String date = year + "-" + formattedMonth + "-" + formatDay;

                            binding.endDateTxttt.setText(date);
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                });

            }
        });


        binding.createJobsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrJTitle = binding.jbtitle.getText().toString();
                StrDaily = binding.DailyRateTxtfdfs.getText().toString();
                StrHourlyTime = binding.HourlyTime.getText().toString();
                StrStartDate = binding.startDateTxttt.getText().toString();
                StrEndDate = binding.endDateTxttt.getText().toString();
                StrSummary = binding.JobsDescriptiontxtgf.getText().toString();
                Straddress = binding.AddressTxtfdfs.getText().toString();

                createJobApi();
            }
        });
    }

    private void createJobApi() {
        final ProgressDialog pd = new ProgressDialog(CreateJobActivity.this);
        pd.setCancelable(false);
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<CreateJobModel> call = service.CREATE_JOB_MODEL_CALL(UserId, catIds, StrJTitle, StrSummary, UserType, StrDaily, StrStartDate, StrEndDate, StrHourlyTime, Straddress);
        call.enqueue(new Callback<CreateJobModel>() {
            @Override
            public void onResponse(Call<CreateJobModel> call, Response<CreateJobModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        CreateJobModel createJobModel = response.body();
                        String success = createJobModel.getSuccess();
                        String msg = createJobModel.getMessage();
                        Log.e("hello", "success: " + success);

                        if (success.equals("true") || (success.equals("True"))) {

                            Log.e("hello", "getData: ");


                            // Id  = profileGetData.getId();

                            Toast.makeText(CreateJobActivity.this, msg, Toast.LENGTH_LONG).show();
                            // Calling another activity

                        } else {
                            Toast.makeText(CreateJobActivity.this, msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(CreateJobActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(CreateJobActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(CreateJobActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(CreateJobActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(CreateJobActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(CreateJobActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(CreateJobActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(CreateJobActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(CreateJobActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(CreateJobActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CreateJobModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(CreateJobActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(CreateJobActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }

    private void getCategoryApi() {

        final ProgressDialog pd = new ProgressDialog(CreateJobActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<GetCategoryModel> call = service.GET_CATEGORY_MODEL_CALL();

        call.enqueue(new Callback<GetCategoryModel>() {
            @Override
            public void onResponse(Call<GetCategoryModel> call, Response<GetCategoryModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        String success = response.body().getSuccess();
                        String message = response.body().getMessage();
                        Log.e("hello", "success: " + success);

                        if (success.equals("True") || success.equals("true")) {
                            GetCategoryModel getCategoryModel = response.body();
                            getCategoryModelDataList = getCategoryModel.getData();

                            for (int i = 0; i < getCategoryModelDataList.size(); i++) {

                                CatList2.add(getCategoryModelDataList.get(i).getCatName());
                                //CatList1.clear();

                                Log.e("catIds", "onResponse: " + catIds);

                            }
                            CreateJobActivity.spinnerAdapter dAdapter = new spinnerAdapter(CreateJobActivity.this, R.layout.filter_custom_spinner, CatList2);
                            dAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            dAdapter.addAll(CatList2);

                            dAdapter.add(getResources().getString(R.string.JobProfile));
                            binding.jobProfileSpinner.setAdapter(dAdapter);
                            binding.jobProfileSpinner.setSelection(dAdapter.getCount());

                            Log.e("hello", "getData: ");
                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(getApplicationContext(), "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(getApplicationContext(), "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(getApplicationContext(), "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(getApplicationContext(), "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(getApplicationContext(), "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(getApplicationContext(), "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(getApplicationContext(), "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCategoryModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(getApplicationContext(), "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }

    public class spinnerAdapter extends ArrayAdapter<String> {
        private spinnerAdapter(Context context, int textViewResourceId, List<String> smonking) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }
    }
}