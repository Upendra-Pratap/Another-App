package com.app.insphired.EmployeeActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.app.insphired.EmployeeActivity.BankDetailsViewModel.Bankdetails;
import com.app.insphired.EmployeeActivity.BankDetailsViewModel.BankdetailsData;
import com.app.insphired.EmployeeActivity.BankDetailsViewModel.GetBankdetails;
import com.app.insphired.EmployeeActivity.BankDetailsViewModel.GetBankdetailsData;
import com.app.insphired.R;
import com.app.insphired.retrofit.Api;
import com.app.insphired.retrofit.Api_Client;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankAccountActivity extends AppCompatActivity {
    EditText AccNameEditext,bankName,IfscCodee,AccNoEmoployee;
    ImageView hiddenBankDetails,showBankDetails,backArrowAddAccount;
    AppCompatButton submitBtnBankDetails;
    private String strAccHolName,strBankName,strIfscCode,strAccNumber,user_id,UserType,StrGetAccHolderName,StrGetIfscCode,StrGetBankName,StrGetAccNumber;
    BankdetailsData bankdetailsData;
    GetBankdetailsData getBankdetailsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account);
        inits();
        backArrowAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        hiddenBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hiddenBankDetails.setVisibility(View.GONE);
                showBankDetails.setVisibility(View.VISIBLE);
                AccNoEmoployee.setTransformationMethod(null);
                AccNoEmoployee.setSelection(AccNoEmoployee.getText().length());

            }
        });
        showBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBankDetails.setVisibility(View.GONE);
                hiddenBankDetails.setVisibility(View.VISIBLE);
                AccNoEmoployee.setTransformationMethod(new PasswordTransformationMethod());
                AccNoEmoployee.setSelection(AccNoEmoployee.getText().length());

            }
        });

        SharedPreferences getUserIdData = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        user_id = getUserIdData.getString("Id", "");
        UserType = getUserIdData.getString("userType", "");

        getBankDetailsApi();
        submitBtnBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAccHolName = AccNameEditext.getText().toString();
                strBankName = bankName.getText().toString();
                strIfscCode = IfscCodee.getText().toString();
                strAccNumber = AccNoEmoployee.getText().toString();
                if (validation())
                {
                    BankDetailsApi();
                }
            }
        });


    }

    private void BankDetailsApi() {
        ProgressDialog progressDialog = new ProgressDialog(BankAccountActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("loading");
        progressDialog.show();

        Api service = Api_Client.getClient().create(Api.class);
        Call<Bankdetails> call = service.BANKDETAILS_CALL(user_id,strAccHolName,strBankName,strIfscCode,strAccNumber);
        call.enqueue(new Callback<Bankdetails>() {
            @Override
            public void onResponse(Call<Bankdetails> call, Response<Bankdetails> response) {
                progressDialog.dismiss();

                try {
                    if (response.isSuccessful())
                    {
                        Bankdetails bankdetails = response.body();
                        String success = bankdetails.getSuccess();
                        String msg   = bankdetails.getMessage();

                        if (success.equals("true")|| success.equals("true"))
                        {
                          BankdetailsData bankdetailsData = bankdetails.getData();
                            Toast.makeText(BankAccountActivity.this, msg, Toast.LENGTH_SHORT).show();
                            getBankDetailsApi();
                        }

                        else
                        {
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Log.e("user_id", "    False");
                        }
                    }
                    else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Log.e("user_id", "    Message");
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

                            Log.e("user_id", "    Exception");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    Log.e("user_id", "    Exception  " + e.getMessage() + "  " + e.toString());
                }

            }

            @Override
            public void onFailure(Call<Bankdetails> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(getApplicationContext(), "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });





    }

    private void getBankDetailsApi() {

        final ProgressDialog pd = new ProgressDialog(BankAccountActivity.this);
        pd.setCancelable(false);
        pd.setMessage("loading...");
        pd.show();
        Api service = Api_Client.getClient().create(Api.class);
        Call<GetBankdetails> call = service.GET_BANKDETAILS_CALL("get_bank?user_id="+user_id);

        call.enqueue(new Callback<GetBankdetails>() {
            @Override
            public void onResponse(Call<GetBankdetails> call, Response<GetBankdetails> response) {
                pd.dismiss();
                try {
                    //if api response is successful ,taking message and success
                    if (response.isSuccessful()) {
                        //String message = response.body().getMessage();
                        String success = response.body().getSuccess();
                        String message = response.body().getMessage();
                        Log.e("hello", "success: " +success );

                        if (success.equals("True") || success.equals("true")) {
                            GetBankdetails getBankdetails = response.body();
                            getBankdetailsData = getBankdetails.getData();
                            StrGetAccHolderName = getBankdetailsData.getAccHolderName();
                            StrGetBankName = getBankdetailsData.getBankName();
                            StrGetIfscCode = getBankdetailsData.getiFSCCode();
                            StrGetAccNumber = getBankdetailsData.getAccNumber();

                            AccNameEditext.setText(StrGetAccHolderName);
                            bankName.setText(StrGetBankName);
                            IfscCodee.setText(StrGetIfscCode);
                            AccNoEmoployee.setText(StrGetAccNumber);

                            Log.e("hello", "getData: " );
                            // Id  = profileGetData.getId()



                        } else {
                            // Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(BankAccountActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                            switch (response.code()) {
                                case 400:
                                    Toast.makeText(BankAccountActivity.this, "The server did not understand the request.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 401:
                                    Toast.makeText(BankAccountActivity.this, "Unauthorized The requested page needs a username and a password.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 404:
                                    Toast.makeText(BankAccountActivity.this, "The server can not find the requested page.", Toast.LENGTH_SHORT).show();
                                    break;
                                case 500:
                                    Toast.makeText(BankAccountActivity.this, "Internal Server Error..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 503:
                                    Toast.makeText(BankAccountActivity.this, "Service Unavailable..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 504:
                                    Toast.makeText(BankAccountActivity.this, "Gateway Timeout..", Toast.LENGTH_SHORT).show();
                                    break;
                                case 511:
                                    Toast.makeText(BankAccountActivity.this, "Network Authentication Required ..", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(BankAccountActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } catch (Exception e) {
                            Toast.makeText(BankAccountActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetBankdetails> call, Throwable t) {
                Log.e("conversion issue", t.getMessage());

                if (t instanceof IOException) {
                    Toast.makeText(BankAccountActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    Log.e("conversion issue", t.getMessage());
                    Toast.makeText(BankAccountActivity.this, "Please Check your Internet Connection...." + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });


    }

    private boolean validation() {
        if (AccNameEditext.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (bankName.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter your bank name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (IfscCodee.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter your IFSC Code", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (AccNoEmoployee.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please enter your account number", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void inits() {
        AccNameEditext = findViewById(R.id.AccNameEditext);
        bankName = findViewById(R.id.bankName);
        IfscCodee = findViewById(R.id.IfscCodee);
        AccNoEmoployee = findViewById(R.id.AccNoEmoployee);
        hiddenBankDetails = findViewById(R.id.hiddenBankDetails);
        showBankDetails = findViewById(R.id.showBankDetails);
        submitBtnBankDetails = findViewById(R.id.submitBtnBankDetails);
        backArrowAddAccount = findViewById(R.id.backArrowAddAccount);
    }
}