package ir.ac.shirazu.attendance_system.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonObject;

import ir.ac.shirazu.attendance_system.Api.UserApi;
import ir.ac.shirazu.attendance_system.Models.User;
import ir.ac.shirazu.attendance_system.R;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button reportbtn = (Button) findViewById(R.id.main_report_btn);
        Button repadminbtn = (Button)findViewById(R.id.main_admin_report_btn);
        Button requestAdmissionButton = (Button) findViewById(R.id.main_request_admission);

        UserApi.signup(new User("ali1", "gsdf1"), this);

        UserApi.login(new User("ali", "gsdf"), this);

//        UserApi.checkAuth(this);

        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, UserReportActivity.class);
                startActivity(myIntent);
            }
        });

        repadminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, adminReportActivity.class);
                startActivity(myIntent);
            }
        });

        requestAdmissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, RequestActivity.class);
                startActivity(myIntent);
            }
        });

    }



}
