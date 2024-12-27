package com.app.insphired.FirstInterfaceApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.insphired.EmployerActivity.RegisterActivity;
import com.app.insphired.R;

public class EmployerJobPostActivity extends AppCompatActivity {
    private ImageView imgbackEmployer;
    private LinearLayout linearTemporaryJobPost,linearPermanentJPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_job_post);

        imgbackEmployer = findViewById(R.id.imgbackEmployer);
        linearTemporaryJobPost = findViewById(R.id.linearTemporaryJobPost);
        linearPermanentJPost = findViewById(R.id.linearPermanentJPost);

        imgbackEmployer.setOnClickListener(v -> finish());

        linearTemporaryJobPost.setOnClickListener(v -> {
            Intent intent = new Intent(EmployerJobPostActivity.this, RegisterActivity.class);
            intent.putExtra("TempEmployer","TemJPost");
            startActivity(intent);
        });

        linearPermanentJPost.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://insphired.jobs/active-jobs/"));
            startActivity(browserIntent);
        });

    }
}