package com.app.insphired.FirstInterfaceApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.insphired.EmployerActivity.LoginActivity;
import com.app.insphired.R;

public class FirstInterfaceAppActivity extends AppCompatActivity {
    ImageView backImgAre;
    private LinearLayout linearjobseekerrrr, linearEmployer, linearCorporatorrr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_interface_app);
        linearjobseekerrrr = findViewById(R.id.linearjobseekerrrr);
        linearEmployer = findViewById(R.id.linearEmployer);
        linearCorporatorrr = findViewById(R.id.linearCorporatorrr);
        //  backImgAre = findViewById(R.id.backImgAre);
       /* backImgAre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        linearjobseekerrrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstInterfaceAppActivity.this, JobSeekerActivity.class);
                startActivity(intent);
            }
        });

        linearEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstInterfaceAppActivity.this, EmployerJobPostActivity.class);
                startActivity(intent);
            }
        });

        linearCorporatorrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstInterfaceAppActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


}
