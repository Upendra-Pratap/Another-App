package com.app.insphired.EmployeeActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.insphired.R;
import com.app.insphired.adapters.RequestedJobsAdapter;
import com.app.insphired.apimodel.GetRequestE;
import com.app.insphired.apimodel.GetRequestEData;
import com.app.insphired.retrofit.Api;
import com.app.insphired.retrofit.Api_Client;


import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestJobsActivity extends AppCompatActivity {
    ImageView backArrowRequestJobs;
    RecyclerView recycylerRequestJobs;
    List<GetRequestEData> getRequestEDataList = new ArrayList<>();
    RequestedJobsAdapter requestedJobsAdapter;

    private String user_id,UserType;
    LinearLayout ReqjobLinearrr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_jobs);

        backArrowRequestJobs= findViewById(R.id.backArrowRequestJobs);
        recycylerRequestJobs= findViewById(R.id.recycylerRequestJobs);
        ReqjobLinearrr= findViewById(R.id.ReqjobLinearrr);

        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        user_id = getUserIdData.getString("Id", "");
        UserType = getUserIdData.getString("userType", "");

        GetRequestedJobs();

        backArrowRequestJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RequestJobsActivity.this,LinearLayoutManager.VERTICAL,false);
        recycylerRequestJobs.setLayoutManager(layoutManager);
    }

    private void  GetRequestedJobs() {
        final ProgressDialog pd = new ProgressDialog(RequestJobsActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();

        Api service = Api_Client.getClient().create(Api.class);
        Call<GetRequestE> call = service.GET_REQUEST_E_CALL("get_request_data?user_id="+user_id);

        call.enqueue(new Callback<GetRequestE>() {
            @Override
            public void onResponse(Call<GetRequestE> call, Response<GetRequestE> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        GetRequestE getRequestE = response.body();
                        String success = getRequestE.getSuccess();
                        String msg =getRequestE.getMessage();
                        Log.e("hello", "success: " +success );

                        if (success.equals("true")|| (success.equals("True"))) {
                            getRequestEDataList = getRequestE.getData();
                            if(getRequestEDataList.isEmpty())
                            {
                                ReqjobLinearrr.setVisibility(View.VISIBLE);
                                recycylerRequestJobs.setVisibility(View.GONE);
                            }
                            else {
                                requestedJobsAdapter = new RequestedJobsAdapter(RequestJobsActivity.this,getRequestEDataList,UserType);
                                recycylerRequestJobs.setAdapter(requestedJobsAdapter);
                            }


                            Log.e("hello", "getData: " );

                        } else {
                            Toast.makeText(RequestJobsActivity.this, msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(RequestJobsActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(RequestJobsActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(RequestJobsActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(RequestJobsActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(RequestJobsActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(RequestJobsActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(RequestJobsActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(RequestJobsActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(RequestJobsActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(RequestJobsActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetRequestE> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(RequestJobsActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(RequestJobsActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }
}