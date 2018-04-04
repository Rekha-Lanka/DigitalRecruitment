package com.company.mansopresk.digitalrecruitment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.company.mansopresk.digitalrecruitment.RegisterActivity.key;

/**
 * Created by satya computers on 3/23/2018.
 */

public class MainActivity extends Activity {
    Bitmap bt;
    ImageView iv2;
    Bitmap byteArray;
    String encodedImage;
    SharedPreferences sharedPreferences;

    SharedPreferences shre;
    TextView mainrefid, mainname;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        mainname = findViewById(R.id.mainname);
        mainrefid = findViewById(R.id.mainrefid);
        iv2 = (ImageView) findViewById(R.id.iv2);
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);




        String img_str = preferences.getString("userphoto", "");
        if (!img_str.equals("")) {
            //decode string to image
            String base = img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            iv2.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        }





        shre = getSharedPreferences("userdetails", MODE_PRIVATE);
            String loginid = shre.getString("refid", null);
            String name=shre.getString("rname",null);
            mainname.setText(name);
            mainrefid.setText(loginid);
            findViewById(R.id.alljobs).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Jobs.class);
                    startActivity(i);
                }
            });
            findViewById(R.id.applied).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, JobDetailsActivity.class);
                    startActivity(i);
                }
            });
            findViewById(R.id.placements).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, PlacementsActivity.class);
                    startActivity(i);
                }
            });
            findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    getApplicationContext().getSharedPreferences("userdetails", 0).edit().clear().commit();
                    getApplicationContext().getSharedPreferences("regdetails", 0).edit().clear().commit();
                    getApplicationContext().getSharedPreferences("myprefs",0).edit().clear().commit();
                    startActivity(i);
                }
            });
            findViewById(R.id.viewprofile).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ViewProfileActivity.class);
                    startActivity(i);
                }
            });
        }
        @Override
        public void onBackPressed () {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


        }
    }

