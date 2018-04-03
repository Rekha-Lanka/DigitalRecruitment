package com.company.mansopresk.digitalrecruitment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity {
  Button login;
  TextView registernow;
  EditText etmemid,etmobile,etpwd;
  String memId,mobileno;
  SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etmemid = findViewById(R.id.lmemid);
        etmobile = findViewById(R.id.lmobile);
        etpwd = findViewById(R.id.lpwd);

        login = findViewById(R.id.login);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);



        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etmemid.getText().toString().isEmpty()) {
                    etmemid.requestFocus();
                    etmemid.setError("");
                } else if (etmobile.getText().toString().isEmpty()) {
                    etmobile.requestFocus();
                    etmobile.setError("");
                } else if (etpwd.getText().toString().isEmpty()) {
                    etpwd.requestFocus();
                    etpwd.setError("");

                } else {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    SharedPreferences preferences = getSharedPreferences("userdetails", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    memId = etmemid.getText().toString();
                    //  pass = edtpass.getText().toString();
                    editor.putString("refid", memId);
                    //editor.putString("fullname",username);
                    editor.commit();
                    startActivity(i);
                }
            }
        });



        findViewById(R.id.registernow).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //@Override
        //public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // }
    }


}

