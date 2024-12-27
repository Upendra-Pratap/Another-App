package com.app.insphired.FirstInterfaceApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.insphired.EmployerActivity.RegisterActivity;
import com.app.insphired.R;

public class JobSeekerActivity extends AppCompatActivity {
    private ImageView imgbackJobseeker;
    LinearLayout linearTempraryUnique,linearPermanentUnique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker);
        imgbackJobseeker = findViewById(R.id.imgbackJobseeker);
        linearTempraryUnique = findViewById(R.id.linearTempraryUnique);
        linearPermanentUnique = findViewById(R.id.linearPermanentUnique);


        imgbackJobseeker.setOnClickListener(v -> finish());

        linearTempraryUnique.setOnClickListener(v -> {
            Intent intent = new Intent(JobSeekerActivity.this, RegisterActivity.class);
            intent.putExtra("TempEmployer","jobseeker");
            startActivity(intent);
        });

        linearPermanentUnique.setOnClickListener(v -> {
            //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://itdevelopmentservices.com/insphire/"));
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://insphired.jobs/active-jobs/"));
            startActivity(browserIntent);
        });
    }
}