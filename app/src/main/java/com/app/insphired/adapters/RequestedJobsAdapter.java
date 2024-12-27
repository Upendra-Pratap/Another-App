package com.app.insphired.adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.app.insphired.EmployeeActivity.DashboardActivityEmployee;
import com.app.insphired.R;
import com.app.insphired.apimodel.AcceptRequestModel;
import com.app.insphired.apimodel.AcceptRequestModelData;
import com.app.insphired.apimodel.GetRequestEData;
import com.app.insphired.apimodel.RejectRequestJobModel;
import com.app.insphired.apimodel.RejectRequestJobModelData;
import com.app.insphired.retrofit.Api;
import com.app.insphired.retrofit.Api_Client;
import com.bumptech.glide.Glide;


import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestedJobsAdapter extends RecyclerView.Adapter<RequestedJobsAdapter.ViewHolder> {
    Context context;
    List<GetRequestEData> getRequestEDataList;

    AcceptRequestModelData acceptRequestModelData;

    RejectRequestJobModelData rejectRequestJobModelData;
    String UserType;
    Integer is_corporator;

    public RequestedJobsAdapter(Context context, List<GetRequestEData> getRequestEDataList, String userType) {
        this.context = context;
        this.getRequestEDataList = getRequestEDataList;
        UserType = userType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.recycelrrequestjobs,parent,Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        is_corporator = getRequestEDataList.get(position).getIsCorporator();
        if (is_corporator.equals(0))
        {

            //Glide.with(context).load(Api_Client.BASE_URL_IMAGES2+myJobModelDataList.get(position).getCompanyImage()).into(holder.imgMyjobss);
            Glide.with(context).load(getRequestEDataList.get(position).getCompanyImage()).into(holder.imgMyjobsss);
            //Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Glide.with(context).load(getRequestEDataList.get(position).getCompanyImage()).into(holder.imgMyjobsss);
            //Glide.with(context).load(Api_Client.BASE_URL_IMAGES1+myJobModelDataList.get(position).getCompanyImage()).into(holder.imgMyjobss);
        }


        Log.e("images", " " + getRequestEDataList.get(position).getCompanyImage());
        holder.nameMyjobsss.setText(getRequestEDataList.get(position).getCatName());
        holder.profileNamejobsss.setText(getRequestEDataList.get(position).getCompanyName());
        holder.joiningDateHJobsss.setText(getRequestEDataList.get(position).getJoiningDate());
        holder.endDateHJobsss.setText(getRequestEDataList.get(position).getEndDate());
        holder.amountMyjobsss.setText(getRequestEDataList.get(position).getDailyRate());
        holder.locationJobsss.setText(getRequestEDataList.get(position).getCompanyAddress());

        holder.rejectBtnReqjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  Id = String.valueOf(getRequestEDataList.get(position).getId());
                String Page = String.valueOf(getRequestEDataList.get(position).getPage());
                String UserType = String.valueOf(getRequestEDataList.get(position).getUserType());
                RejectJobApi(Id,position,Page,UserType);
            }
        });

        holder.acceptBtnReqjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  Id = String.valueOf(getRequestEDataList.get(position).getId());
                String UserId = String.valueOf(getRequestEDataList.get(position).getId());
                String Page = String.valueOf(getRequestEDataList.get(position).getPage());
                String UserType = String.valueOf(getRequestEDataList.get(position).getUserType());
                AcceptRequestedJobs(Id,position,UserId,Page,UserType);
            }
        });
    }

    private void  AcceptRequestedJobs(String id,int position1, String userId, String page, String userType) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        Call<AcceptRequestModel> call = service.ACCEPT_REQUEST_MODEL_CALL(id,userId,page,userType);

        call.enqueue(new Callback<AcceptRequestModel>() {
            @Override
            public void onResponse(Call<AcceptRequestModel> call, Response<AcceptRequestModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        AcceptRequestModel acceptRequestModel = response.body();
                        String success = acceptRequestModel.getSuccess();
                        String msg = acceptRequestModel.getMessage();
                        Log.e("hello", "success: " +success );

                        if (success.equals("true") || success.equals("True")) {
                            acceptRequestModelData = acceptRequestModel.getData();
                            Intent intent = new Intent(context, DashboardActivityEmployee.class);
                            context.startActivity(intent);
                           // Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            Log.e("hello", "getData: " );

                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(context, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(context, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(context, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(context, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(context, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(context, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(context, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(context, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(context, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AcceptRequestModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(context, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(context, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }

        });


    }
    private void  RejectJobApi(String idd, int position, String page, String userType) {

        final ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        Call<RejectRequestJobModel> call = service.REJECT_REQUEST_JOB_MODEL_CALL(idd,page,userType);

        call.enqueue(new Callback<RejectRequestJobModel>() {
            @Override
            public void onResponse(Call<RejectRequestJobModel> call, Response<RejectRequestJobModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        RejectRequestJobModel RejectRequestJobModel = response.body();
                        String success = RejectRequestJobModel.getSuccess();
                        String msg = RejectRequestJobModel.getMessage();
                        Log.e("hello", "success: " +success );

                        if (success.equals("true")|| (success.equals("True"))) {
                            rejectRequestJobModelData = RejectRequestJobModel.getData();
                            String status = String.valueOf(rejectRequestJobModelData.getStatus());
                            removeItem(position);
                            Intent intent = new Intent(context, DashboardActivityEmployee.class);
                            context.startActivity(intent);

                          //  Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId();

                            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            // Calling another activity

                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(context, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(context, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(context, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(context, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(context, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(context, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(context, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(context, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(context, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RejectRequestJobModel> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(context, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(context, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }
    @SuppressLint("NotifyDataSetChanged")
    public void removeItem(int position) {
        try {
            getRequestEDataList.remove(position);
            notifyDataSetChanged();     // Update data in adapter.... Notify adapter for change data
        } catch (IndexOutOfBoundsException index) {
            index.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return getRequestEDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearFindJobs;
        CircleImageView imgMyjobsss;
        TextView nameMyjobsss, profileNamejobsss, joiningDateHJobsss, endDateHJobsss, amountMyjobsss, locationJobsss,
                statusMyJobsss, paymentMyJobsss;
        AppCompatButton acceptBtnReqjobs, rejectBtnReqjobs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMyjobsss = itemView.findViewById(R.id.imgMyjobsss);
            nameMyjobsss = itemView.findViewById(R.id.nameMyjobsss);
            profileNamejobsss = itemView.findViewById(R.id.profileNamejobsss);
            joiningDateHJobsss = itemView.findViewById(R.id.joiningDateHJobsss);
            endDateHJobsss = itemView.findViewById(R.id.endDateHJobsss);
            amountMyjobsss = itemView.findViewById(R.id.amountMyjobsss);
            locationJobsss = itemView.findViewById(R.id.locationJobsss);
            statusMyJobsss = itemView.findViewById(R.id.statusMyJobsss);
            paymentMyJobsss = itemView.findViewById(R.id.paymentMyJobsss);
            acceptBtnReqjobs = itemView.findViewById(R.id.acceptBtnReqjobs);
            rejectBtnReqjobs = itemView.findViewById(R.id.rejectBtnReqjobs);
        }
    }

}
