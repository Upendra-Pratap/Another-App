package com.app.insphired.EmployeeActivity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.customtoast.chen.customtoast.CustomToast;
import com.app.insphired.apimodel.CreateSlotModel;
import com.app.insphired.apimodel.CreateSlotModelData;
import com.app.insphired.R;
import com.app.insphired.retrofit.Api;
import com.app.insphired.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSlotActivity extends AppCompatActivity {

    CreateSlotModelData createSlotModelData;
    private ImageView backArrowCSlots, imgMapC;
    private LinearLayout linearStartDate, linearEndDate, linearStartTime, linearEndTime;
    private TextView startDateTxt, endDateTxt, startTimetxt, endTimeTxt, selectLocationC;
    private int year, month, day;
    private AppCompatButton createSlotsBtn;
    private String StrStartDate, StrStartTime, StrEndDate, StrEndTime, UserId;
    private String Id;
    private CustomToast customToast;
    private RelativeLayout relativeMapssss;

    private String strAddresss,StrselectLocationC,lcStartDate,lcStartTime,lcEndDate,lcEndTime;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_slot);
        inits();

        backArrowCSlots.setOnClickListener(v -> finish());

        relativeMapssss.setOnClickListener(v -> {
            Intent intent = new Intent(CreateSlotActivity.this, LocationActivity.class);
            intent.putExtra("StartDate",startDateTxt.getText().toString());
            intent.putExtra("StartTime",startTimetxt.getText().toString());
            intent.putExtra("EndDate",endDateTxt.getText().toString());
            intent.putExtra("EndTime",endTimeTxt.getText().toString());
            startActivity(intent);
        });

        strAddresss = getIntent().getStringExtra("searchValue");
        lcStartDate = getIntent().getStringExtra("locStartDate");
        lcStartTime = getIntent().getStringExtra("locStartTime");
        lcEndDate = getIntent().getStringExtra("locEndDate");
        lcEndTime = getIntent().getStringExtra("locEndTime");

        if (lcStartDate == null) {
            startDateTxt.setText("Start Date");
        } else {
            startDateTxt.setText(lcStartDate);
        }

        if (lcStartTime == null) {
            startTimetxt.setText("Start Time");
        } else {
            startTimetxt.setText(lcStartTime);
        }

        if (lcEndDate == null) {
            endDateTxt.setText("End Date");
        } else {
            endDateTxt.setText(lcEndDate);
        }

        if (lcEndTime == null) {
            endTimeTxt.setText("End Time");
        } else {
            endTimeTxt.setText(lcEndTime);
        }

        selectLocationC.setText(strAddresss);


        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", MODE_PRIVATE);
        UserId = getUserIdData.getString("Id", "");
        Log.e("feedback", "change" + UserId);

        createSlotsBtn.setOnClickListener(v -> {

            StrStartDate = startDateTxt.getText().toString();
            StrStartTime = startTimetxt.getText().toString();
            StrEndDate = endDateTxt.getText().toString();
            StrEndTime = endTimeTxt.getText().toString();
            StrselectLocationC = selectLocationC.getText().toString();
            if (validation()) {
                CreateSlotsApi();
            }

        });

        linearStartDate.setOnClickListener(v -> {

            final Calendar calendar = Calendar.getInstance();

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            Log.e("month", "kkk.." + month);

            //calendar.add(Calendar.MONTH, 1);

            linearStartDate.setOnClickListener(view -> {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateSlotActivity.this, R.style.MyDatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;

                        String formattedMonth = String.format("%02d", month);
                        String formatDay = String.format("%02d", day);
                        String date = year + "-" + formattedMonth + "-" + formatDay;

                        startDateTxt.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            });

        });

        linearEndDate.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                Log.e("month", "kkk.." + month);

                //calendar.add(Calendar.MONTH, 1);

                linearEndDate.setOnClickListener(view -> {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateSlotActivity.this, R.style.MyDatePicker, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            month = month + 1;

                            String formattedMonth = String.format("%02d", month);
                            String formatDay = String.format("%02d", day);
                            String date = year + "-" + formattedMonth + "-" + formatDay;

                            endDateTxt.setText(date);
                        }
                    }, year, month, day);
                    datePickerDialog.show();
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                    datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                });

            }
        });

        linearStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    mcurrentTime = Calendar.getInstance();
                }
                int hour = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                }
                int minute = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    minute = mcurrentTime.get(Calendar.MINUTE);
                }
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateSlotActivity.this, R.style.MyTimePicker, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String formattedhour = String.format("%02d", selectedHour);
                        String formatMinutes = String.format("%02d", selectedMinute);

                        startTimetxt.setText(formattedhour + ":" + formatMinutes);
                    }
                }, hour, minute, true);//Yes 24 hour time
                // mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        linearEndTime.setOnClickListener(v -> {
            Calendar mcurrentTime = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mcurrentTime = Calendar.getInstance();
            }
            int hour = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            }
            int minute = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                minute = mcurrentTime.get(Calendar.MINUTE);
            }
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(CreateSlotActivity.this, R.style.MyTimePicker, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    String formattedhour = String.format("%02d", selectedHour);
                    String formatMinutes = String.format("%02d", selectedMinute);
                    endTimeTxt.setText(formattedhour + ":" + formatMinutes);
                }
            }, hour, minute, true);//Yes 24 hour time
            //   mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });
    }

    private void inits() {
        backArrowCSlots = findViewById(R.id.backArrowCSlots);
        linearStartDate = findViewById(R.id.linearStartDate);
        linearEndDate = findViewById(R.id.linearEndDate);
        startDateTxt = findViewById(R.id.startDateTxt);
        endDateTxt = findViewById(R.id.endDateTxt);
        linearStartTime = findViewById(R.id.linearStartTime);
        linearEndTime = findViewById(R.id.linearEndTime);
        startTimetxt = findViewById(R.id.startTimetxt);
        endTimeTxt = findViewById(R.id.endTimeTxt);
        createSlotsBtn = findViewById(R.id.createSlotsBtn);
        imgMapC = findViewById(R.id.imgMapC);
        selectLocationC = findViewById(R.id.selectLocationC);
        relativeMapssss = findViewById(R.id.relativeMapssss);
        // locationSpinner = findViewById(R.id.locationSpinner);

    }
    private void CreateSlotsApi() {
        final ProgressDialog pd = new ProgressDialog(CreateSlotActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<CreateSlotModel> call = service.CREATE_SLOT_MODEL_CALL(UserId,StrStartDate,StrEndDate,StrStartTime,StrEndTime,strAddresss);

        call.enqueue(new Callback<CreateSlotModel>() {
            @Override
            public void onResponse(@NonNull Call<CreateSlotModel> call, @NonNull Response<CreateSlotModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        CreateSlotModel createSlotModel = response.body();
                        String success = createSlotModel.getSuccess();
                        String msg = createSlotModel.getMessage();
                        Log.e("hello", "success: " + success);

                        if (success.equals("true") || (success.equals("True"))) {
                            createSlotModelData = response.body().getData();
                            Id = String.valueOf(createSlotModelData.getId());
                            /*  startDateTxt.setText("");
                             startTimetxt.setText("");
                            endDateTxt.setText("");
                             endTimeTxt.setText("");*/

                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(CreateSlotActivity.this, AvailableSlotsActivity.class);
                            startActivity(intent);
                            Log.e("hello", "getData: ");
                            // Id  = profileGetData.getId();
                        } else {
                         /*   customToast = new CustomToast(CreateSlotActivity.this); //pass context as parameter
                            customToast.setTextColor(CreateSlotActivity.this.getResources().getColor(R.color.black));
                            customToast.setBackground(CreateSlotActivity.this.getResources().getColor(R.color.red));
                            customToast.showErrorToast(msg);*/
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<CreateSlotModel> call, Throwable t) {
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

    private boolean validation() {
        if ((startDateTxt.getText().toString().isEmpty()) || startDateTxt.getText().toString().equals("Start Date")) {
            Toast.makeText(getApplicationContext(), "Please enter start date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if  ((startTimetxt.getText().toString().isEmpty()) || startTimetxt.getText().toString().equals("Start Time")) {
            Toast.makeText(getApplicationContext(), "Please enter start time", Toast.LENGTH_SHORT).show();
            return false;
        } else if  ((endDateTxt.getText().toString().isEmpty()) || endDateTxt.getText().toString().equals("End Date")) {
            Toast.makeText(getApplicationContext(), "Please enter end date", Toast.LENGTH_SHORT).show();
            return false;
        } else if((endTimeTxt.getText().toString().isEmpty()) || endTimeTxt.getText().toString().equals("End Time")) {
            Toast.makeText(getApplicationContext(), "Please enter end time", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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