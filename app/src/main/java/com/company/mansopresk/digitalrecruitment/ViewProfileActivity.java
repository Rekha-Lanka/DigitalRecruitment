package com.company.mansopresk.digitalrecruitment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;

public class ViewProfileActivity extends AppCompatActivity {
    SharedPreferences shre;
    EditText refid, stream, etname, dob, caddr, pin, phno, mobileno, email, paddr;
    String srefid, sstream, sname, spwd, sdob, scaddr, spin, sphno, smobile, smail, spaddr, sinstitutespin, squalspin, sapplyspin, slocspin;
    Spinner institutespin, qualspin, applyspin, locspin;
    ImageView iv3;
    public static final int CAM_REQ_CODE = 123;
    public static final int GAL_REQ_CODE = 321;


    public static final int CAM_PERMISSION_ACCESS_CODE = 111;
    public static final String CAM_PERMISSION_NAME[] = {android.Manifest.permission.CAMERA};
    public static final int GAL_PERMISSION_ACCESS_CODE = 222;
    public static final String GAL_PERMISSION_NAME[] = {android.Manifest.permission.READ_EXTERNAL_STORAGE};

    String choice[] = {"CAMERA", "GALLERY"};

    Bitmap bit = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        refid = findViewById(R.id.prefid);
        stream = findViewById(R.id.pstream);
        etname = findViewById(R.id.puname);
        dob = findViewById(R.id.pdob);
        caddr = findViewById(R.id.pcaddress);
        pin = findViewById(R.id.ppin);
       // phno = findViewById(R.id.pphno);
        mobileno = findViewById(R.id.pmobileno);
        email = findViewById(R.id.pemail);
        paddr = findViewById(R.id.paddress);
        institutespin = findViewById(R.id.instutespin1);
        qualspin = findViewById(R.id.pqualification);
//        qualspin=findViewById(R.id.pqualification);
        applyspin = findViewById(R.id.paapply);
        locspin = findViewById(R.id.plocation);
        iv3 = findViewById(R.id.iv3);




        shre = getSharedPreferences("regdetails", MODE_PRIVATE);
        srefid = shre.getString("rrefid", null);
        //  editor.putString("rrefid",srefid);
        sstream = shre.getString("rstream", null);
        sname = shre.getString("rname", null);
        sdob = shre.getString("rdob", null);
        scaddr = shre.getString("rcaddress", null);
        spin = shre.getString("rpin", null);
        //sphno = shre.getString("rphno", null);
        smobile = shre.getString("rmobile", null);
        smail = shre.getString("rmail", null);
        spaddr = shre.getString("rpaddr", null);
        sinstitutespin = shre.getString("rinstitutespin", null);
        sapplyspin = shre.getString("rapplyspin", null);
        slocspin = shre.getString("rlocationspin", null);
        squalspin = shre.getString("rqualspin", null);
        //stream,etname,dob,caddr,pin,phno,mobileno,email,paddr,institutespin,qualspin,applyspin,locspin
        refid.setText(srefid);
        stream.setText(sstream);
        etname.setText(sname);
        dob.setText(sdob);
        caddr.setText(scaddr);
        pin.setText(spin);
        //phno.setText(sphno);
        mobileno.setText(smobile);
        email.setText(smail);
        paddr.setText(spaddr);
//       institutespin.setText(sinstitutespin);
        institutespin.setSelection(shre.getInt("spinnerSelection", 0));
        qualspin.setSelection(shre.getInt("spinnerSelections", 0));
        applyspin.setSelection(shre.getInt("spinnerSelectionss", 0));
        locspin.setSelection(shre.getInt("spinnerSelectionsss", 0));


//        applyspin.setText(sapplyspin);
//        locspin.setText(slocspin);
//        qualspin.setText(squalspin);
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);

        String img_str = preferences.getString("userphoto", "");
        if (!img_str.equals("")) {
            //decode string to image
            String base = img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            iv3.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        }


    }

    public void photo(View v) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setIcon(R.drawable.rounduser);
        adb.setTitle(" Select One ");
        adb.setItems(choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        int res = ContextCompat.checkSelfPermission(ViewProfileActivity.this, android.Manifest.permission.CAMERA);
                        if (res == PackageManager.PERMISSION_GRANTED) {
                            Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cam, CAM_REQ_CODE);
                        } else {
                            ActivityCompat.requestPermissions(ViewProfileActivity.this, CAM_PERMISSION_NAME, CAM_PERMISSION_ACCESS_CODE);
                        }
                        break;
                    case 1:
                        int res1 = ContextCompat.checkSelfPermission(ViewProfileActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

                        if (res1 == PackageManager.PERMISSION_GRANTED) {
                            Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gal, GAL_REQ_CODE);
                        } else {
                            ActivityCompat.requestPermissions(ViewProfileActivity.this, GAL_PERMISSION_NAME, GAL_PERMISSION_ACCESS_CODE);
                        }

                        break;
                }
            }
        });
        adb.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAM_PERMISSION_ACCESS_CODE:
                if (CAM_PERMISSION_NAME.equals(permissions[0]) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cam, CAM_REQ_CODE);
                }
                break;

            case GAL_PERMISSION_ACCESS_CODE:
                if (GAL_PERMISSION_NAME.equals(permissions[0]) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(gal, GAL_REQ_CODE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case CAM_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle b = intent.getExtras();
                    bit = (Bitmap) b.get("data");
                    iv3.setImageBitmap(bit);
                }
                break;

            case GAL_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    Uri img = intent.getData();
                    try {
                        bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    iv3.setImageBitmap(bit);
                }
                break;


        }
    }
}
