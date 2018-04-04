package com.company.mansopresk.digitalrecruitment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.company.mansopresk.digitalrecruitment.Utils.CameraUtility;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    String image;

    public static final int CAM_REQ_CODE = 123;
    public static final int GAL_REQ_CODE = 321;


    public static final int CAM_PERMISSION_ACCESS_CODE = 111;
    public static final String CAM_PERMISSION_NAME[] = {android.Manifest.permission.CAMERA};
    public static final int GAL_PERMISSION_ACCESS_CODE = 222;
    public static final String GAL_PERMISSION_NAME[] = {android.Manifest.permission.READ_EXTERNAL_STORAGE};


    ImageView iv1;


    String choice[] = {"CAMERA", "GALLERY"};

    Bitmap bit = null;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    //ImageView profilepic;
    CheckBox checkBox;
    EditText refid, stream, etname, etpassword, dob, caddr, pin, phno, mobileno, email, paddr;
    Spinner institutespin, qualspin, applyspin, locspin;
    SharedPreferences shre;
    public static final String key = "nameKey";
    String srefid, sstream, sname, spwd, sdob, scaddr, spin, sphno, smobile, smail, spaddr, sinstitutespin, squalspin, sapplyspin, slocspin;
    static final int REQUEST_CODE_TO_BROWSE_IMAGE = 1;
    SharedPreferences sharedPreferences;
    String encodedImage;
    private CoordinatorLayout coordinatorLayout;
    View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iv1 = findViewById(R.id.iv1);
        refid = findViewById(R.id.refid);
        stream = findViewById(R.id.stream);
        etname = findViewById(R.id.edtname);
        etpassword = findViewById(R.id.edtPass);
        dob = findViewById(R.id.edtdob);
        caddr = findViewById(R.id.caddress);
        pin = findViewById(R.id.edtpin);
        //phno = findViewById(R.id.edtphno);
        mobileno = findViewById(R.id.mobileno);
        email = findViewById(R.id.edtemail);
        paddr = findViewById(R.id.paddress);
        institutespin = findViewById(R.id.instutespin);
        qualspin = findViewById(R.id.eduqualispin);
        applyspin = findViewById(R.id.applyspin);
        locspin = findViewById(R.id.locspin);
        checkBox = findViewById(R.id.checkbox);


        // Initializing an ArrayAdapter



        //getActionBar().setDisplayHomeAsUpEnabled(true);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
//                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(i);
////


            }
        });
    }


    //        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (refid.getText().toString().isEmpty()) {
//                    refid.requestFocus();
//                    refid.setError("");
//                } else if (stream.getText().toString().isEmpty()) {
//                    stream.requestFocus();
//                    stream.setError("");
//                } else if (etname.getText().toString().isEmpty()) {
//                    etname.requestFocus();
//                    etname.setError("");
//
//                } else if (etpassword.getText().toString().isEmpty()) {
//                    etpassword.requestFocus();
//                    etpassword.setError("");
//                } else if (dob.getText().toString().isEmpty()) {
//                    dob.requestFocus();
//                    dob.setError("");
//                } else if (caddr.getText().toString().isEmpty()) {
//                    caddr.requestFocus();
//                    caddr.setError("");
//                } else if (pin.getText().toString().isEmpty()) {
//                    pin.requestFocus();
//                    pin.setError("");
//                } else if (phno.getText().toString().isEmpty()) {
//                    phno.requestFocus();
//                    phno.setError("");
//                } else if (mobileno.getText().toString().isEmpty()) {
//                    mobileno.requestFocus();
//                    mobileno.setError("");
//                } else if (email.getText().toString().isEmpty()) {
//                    email.requestFocus();
//                    email.setError("");
//                } else if (paddr.getText().toString().isEmpty()) {
//                    paddr.requestFocus();
//                    paddr.setError("");
//                }
//
//////                //Spinner mySpinner = (Spinner)findViewById(R.id.spinner_configurable_item);
////                int selectedItemOfMySpinner = institutespin.getSelectedItemPosition();
////                String actualPositionOfMySpinner = (String) institutespin.getItemAtPosition(selectedItemOfMySpinner);
////
////                if (actualPositionOfMySpinner.isEmpty()) {
////                    setSpinnerError(institutespin,"--Select Institute--");
//////                }
//
//
//
//
//
//
//
//                else {
//                    if (checkBox.isChecked()) {
//
//                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//
//
//
//                        SharedPreferences preferences = getSharedPreferences("regdetails", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = preferences.edit();
//                        srefid = refid.getText().toString();
//                        sstream = stream.getText().toString();
//                        sname = etname.getText().toString();
//                        sdob = dob.getText().toString();
//                        scaddr = caddr.getText().toString();
//                        spin = pin.getText().toString();
//                        sphno = phno.getText().toString();
//                        smobile = mobileno.getText().toString();
//                        smail = email.getText().toString();
//                        spaddr = paddr.getText().toString();
//                        sinstitutespin = institutespin.getSelectedItem().toString();
//                        sapplyspin = applyspin.getSelectedItem().toString();
//                        squalspin = qualspin.getSelectedItem().toString();
//                        slocspin = locspin.getSelectedItem().toString();
////
//                        editor.putString("rrefid", srefid);
//                        editor.putString("rstream", sstream);
//                        editor.putString("rname", sname);
//                        editor.putString("rdob", sdob);
//                        editor.putString("rcaddress", scaddr);
//                        editor.putString("rpin", spin);
//                        editor.putString("rphno", sphno);
//                        editor.putString("rmobile", smobile);
//                        editor.putString("rmail", smail);
//                        editor.putString("rpaddr", spaddr);
//                        editor.putString("rinstitutespin", sinstitutespin);
//                        editor.putString("rqualspin", squalspin);
//                        editor.putString("rapplyspin", sapplyspin);
//                        editor.putString("rlocationspin", slocspin);
//
//                        int selectedPosition = institutespin.getSelectedItemPosition();
//                        editor.putInt("spinnerSelection", selectedPosition);
//
//                        int position1 =applyspin.getSelectedItemPosition();
//                        editor.putInt("spinnerSelectionss",position1);
//                        int position2=qualspin.getSelectedItemPosition();
//                        editor.putInt("spinnerSelections",position2);
//                        int position3=locspin.getSelectedItemPosition();
//                        editor.putInt("spinnerSelectionsss",position3);
////
//
//                        editor.commit();
//                        startActivity(i);
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "accept terms and conditions", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
    private boolean validateEmail() {
        //String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(smail);
        if (matcher.matches()) {
            return true;
        } else return false;
    }

    private void prepareText() {
                        srefid = refid.getText().toString();
                        sstream = stream.getText().toString();
                        sname = etname.getText().toString();
                        sdob = dob.getText().toString();
                        scaddr = caddr.getText().toString();
                        spin = pin.getText().toString();
                        //sphno = phno.getText().toString();
                        smobile = mobileno.getText().toString();
                        smail = email.getText().toString();
                        spaddr = paddr.getText().toString();
                        sapplyspin = applyspin.getSelectedItem().toString();
                        squalspin = qualspin.getSelectedItem().toString();
                        slocspin = locspin.getSelectedItem().toString();
///
                  sinstitutespin = institutespin.getSelectedItem().toString();
    }


    public void showSnackbar(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

//    private void showSnackBar(String msg) {
//        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG).show();
//    }

    private boolean validateInput() {
        prepareText();
        if (refid.getText().toString().isEmpty()) {
            showSnackbar(refid, "please enter id", 40000);
            return false;
        } else if (stream.getText().toString().isEmpty()) {
            showSnackbar(stream, "please enter stream", 40000);
            return false;
        } else if (etname.getText().toString().isEmpty()) {
            showSnackbar(etname, "please enter Applicant name", 40000);
            return false;
        } else if (dob.getText().toString().isEmpty()) {
            showSnackbar(dob, "please enter data birth", 40000);
            return false;
        } else if (caddr.getText().toString().isEmpty()) {
            showSnackbar(caddr, "please enter Address", 40000);
            return false;
        } else if (pin.getText().toString().isEmpty()) {
            showSnackbar(pin, "please enter pin code", 40000);
            return false;
        }
//        else if (phno.getText().toString().isEmpty()) {
//            showSnackbar(phno, "please enter Mobile number", 40000);
//            return false;
//        }
        else if (mobileno.equals("")) {
            showSnackbar(mobileno, "Please enter your mobile number", 4000);
            return false;
        } else if (mobileno.length() != 10) {
            showSnackbar(mobileno, "Please enter 10 digit mobile number", 4000);
            return false;
        } else if (paddr.equals("")) {
            showSnackbar(paddr, "Please enter your address", 4000);
            return false;
        } else if (institutespin.getSelectedItemPosition() == 0) {
            showSnackbar(institutespin, "Please select institute", 4000);
            return false;
        } else if (qualspin.getSelectedItemPosition() == 0) {
            showSnackbar(qualspin, "Please select Qualification ", 4000);
            return false;
        } else if (locspin.getSelectedItemPosition() == 0) {
            showSnackbar(locspin, "Please select institute", 4000);
            return false;
        } else if (applyspin.getSelectedItemPosition() == 0) {
            showSnackbar(applyspin, "Please select job role", 4000);
            return false;
        } else if (email.getText().toString().isEmpty()) {
            if (!validateEmail()) {
                showSnackbar(email, "Please enter valid email", 4000);
                return false;
            }
        } else if (checkBox.isChecked()) {

            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            SharedPreferences preferences = getSharedPreferences("regdetails", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("rrefid", srefid);
            editor.putString("rstream", sstream);
            editor.putString("rname", sname);
            editor.putString("rdob", sdob);
            editor.putString("rcaddress", scaddr);
            editor.putString("rpin", spin);
            //editor.putString("rphno", sphno);
            editor.putString("rmobile", smobile);
            editor.putString("rmail", smail);
            editor.putString("rpaddr", spaddr);
            editor.putString("rinstitutespin", sinstitutespin);
            editor.putString("rqualspin", squalspin);
            editor.putString("rapplyspin", sapplyspin);
            editor.putString("rlocationspin", slocspin);

            int selectedPosition = institutespin.getSelectedItemPosition();
            editor.putInt("spinnerSelection", selectedPosition);

            int position1 = applyspin.getSelectedItemPosition();
            editor.putInt("spinnerSelectionss", position1);
            int position2 = qualspin.getSelectedItemPosition();
            editor.putInt("spinnerSelections", position2);
            int position3 = locspin.getSelectedItemPosition();
            editor.putInt("spinnerSelectionsss", position3);
//

            editor.commit();
            startActivity(i);

        }
        else {
            Toast.makeText(this, "please accept terms and conditions", Toast.LENGTH_SHORT).show();
        }

        return true;

    }




    //
    public void camera(View v) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setIcon(R.drawable.rounduser);
        adb.setTitle(" Select One ");
        adb.setItems(choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        int res = ContextCompat.checkSelfPermission(RegisterActivity.this, android.Manifest.permission.CAMERA);
                        if (res == PackageManager.PERMISSION_GRANTED) {
                            Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cam, CAM_REQ_CODE);
                        } else {
                            ActivityCompat.requestPermissions(RegisterActivity.this, CAM_PERMISSION_NAME, CAM_PERMISSION_ACCESS_CODE);
                        }
                        break;
                    case 1:
                        int res1 = ContextCompat.checkSelfPermission(RegisterActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

                        if (res1 == PackageManager.PERMISSION_GRANTED) {
                            Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gal, GAL_REQ_CODE);
                        } else {
                            ActivityCompat.requestPermissions(RegisterActivity.this, GAL_PERMISSION_NAME, GAL_PERMISSION_ACCESS_CODE);
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
                    iv1.setImageBitmap(bit);
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
                    iv1.setImageBitmap(bit);
                }
                break;


        }
        iv1.buildDrawingCache();
        Bitmap bitmap = iv1.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        String img_str = Base64.encodeToString(image, 0);
        //decode string to image
        String base=img_str;
        byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
//        ImageView ivsavedphoto = (ImageView)this.findViewById(R.id.iv2);
//        ivsavedphoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length)
//        );
        SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto",img_str);
        editor.commit();



    }
}












