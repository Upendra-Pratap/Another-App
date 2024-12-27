package com.app.insphired.adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.insphired.EmployeeActivity.NotificationActivity;
import com.app.insphired.EmployeeActivity.NotificationConfirmationActivity;
import com.app.insphired.apimodel.DeleteNotificationModel;
import com.app.insphired.apimodel.ExternalNotificationModel;
import com.app.insphired.apimodel.NotificationModel;
import com.app.insphired.apimodel.NotificationModelData;
import com.app.insphired.R;
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

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context context;
    List<NotificationModelData> notificationModelDataList;

    public NotificationAdapter(Context context, List<NotificationModelData> notificationModelDataList) {
        this.context = context;
        this.notificationModelDataList = notificationModelDataList;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_notification, parent, Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.NotificationHeading.setText(notificationModelDataList.get(position).getSubject());
        holder.NotificationMessage.setText(notificationModelDataList.get(position).getMessage());
        String id = String.valueOf(notificationModelDataList.get(position).getNotificationId());
        String table = String.valueOf(notificationModelDataList.get(position).getTable());
        ;
    /*    holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotificationConfirmationActivity.class);
                context.startActivity(intent);
            }
        });*/
             holder.deleteNotiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //call delete Api
                deleteApi(id,table,position);
            }
        });



    }

    private void deleteApi(String id, String table,int position) {
        String nid = id;
        String tbl = table;
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        retrofit2.Call<DeleteNotificationModel> call = service.DELETE_NOTIFICATION_MODEL_CALL("delete?id="+nid+"&table="+tbl);

        call.enqueue(new Callback<DeleteNotificationModel>() {
            @Override
            public void onResponse(Call<DeleteNotificationModel> call, Response<DeleteNotificationModel> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        String success = (response.body().getSuccess());
                        String msg = (response.body().getMessage());

                        if (success.equals("true") || (success.equals("True"))) {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                            removeItem(position);
                            notifyDataSetChanged();
                            Log.e("hello", "getData: ");

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
            public void onFailure(Call<DeleteNotificationModel> call, Throwable t) {
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
            notificationModelDataList.remove(position);
            notifyDataSetChanged();     // Update data in adapter.... Notify adapter for change data
        } catch (IndexOutOfBoundsException index) {
            index.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return notificationModelDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgCpnyy;
        TextView NotificationHeading, NotificationMessage;
        ImageView deleteNotiImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCpnyy = itemView.findViewById(R.id.imgCpnyy);
            NotificationHeading = itemView.findViewById(R.id.NotificationHeading);
            NotificationMessage = itemView.findViewById(R.id.NotificationMessage);
            deleteNotiImg = itemView.findViewById(R.id.deleteNotiImg);
        }
    }
}
