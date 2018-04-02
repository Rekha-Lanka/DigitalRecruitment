package com.company.mansopresk.digitalrecruitment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.company.mansopresk.digitalrecruitment.Utils.CameraUtility;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RegisterActivity extends Activity {
Button register;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    ImageView profilepic;
EditText refid,stream,etname,etpassword,dob,caddr,pin,phno,mobileno,email,paddr;
Spinner institutespin,qualspin,applyspin,locspin;
SharedPreferences shre;
    public static final String  key = "nameKey";
String srefid,sstream,sname,spwd,sdob,scaddr,spin,sphno,smobile,smail,spaddr,sinstitutespin,squalspin,sapplyspin,slocspin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        profilepic=findViewById(R.id.profilepic);
        refid=findViewById(R.id.refid);
        stream=findViewById(R.id.stream);
        etname=findViewById(R.id.edtname);
        etpassword=findViewById(R.id.edtPass);
        dob=findViewById(R.id.edtdob);
        caddr=findViewById(R.id.caddress);
        pin=findViewById(R.id.edtpin);
        phno=findViewById(R.id.edtphno);
        mobileno=findViewById(R.id.mobileno);
        email=findViewById(R.id.edtemail);
        paddr=findViewById(R.id.paddress);
        institutespin=findViewById(R.id.instutespin);
        qualspin=findViewById(R.id.eduqualispin);
        applyspin=findViewById(R.id.applyspin);
        locspin=findViewById(R.id.locspin);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        register=findViewById(R.id.register);
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                SharedPreferences preferences = getSharedPreferences("regdetails",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                srefid= refid.getText().toString();
                sstream=stream.getText().toString();
                sname=etname.getText().toString();
                sdob=dob.getText().toString();
                scaddr=caddr.getText().toString();
                spin=pin.getText().toString();
                sphno=phno.getText().toString();
                smobile=mobileno.getText().toString();
                smail=email.getText().toString();
                spaddr=paddr.getText().toString();
                sinstitutespin=institutespin.getSelectedItem().toString();
                sapplyspin=applyspin.getSelectedItem().toString();
                squalspin=qualspin.getSelectedItem().toString();
                slocspin=locspin.getSelectedItem().toString();
                editor.putString("rrefid",srefid);
                editor.putString("rstream",sstream);
                editor.putString("rname",sname);
                editor.putString("rdob",sdob);
                editor.putString("rcaddress",scaddr);
                editor.putString("rpin",spin);
                editor.putString("rphno",sphno);
                editor.putString("rmobile",smobile);
                editor.putString("rmail",smail);
                editor.putString("rpaddr",spaddr);
                editor.putString("rinstitutespin",sinstitutespin);
                editor.putString("rqualspin",squalspin);
                editor.putString("rapplyspin",sapplyspin);
                editor.putString("rlocationspin",slocspin);
                //editor.putString("fullname",username);
                editor.commit();
                startActivity(i);

            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CameraUtility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= CameraUtility.checkPermission(RegisterActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask ="Choose from Library";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE){
                //onSelectFromGalleryResult(data);
                Bitmap bm=null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    storeImage(bm);
                    profilepic.setImageBitmap(bm);

                }
            }

            else if (requestCode == REQUEST_CAMERA) {
                //onCaptureImageResult(data);
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                storeImage(bm);
                profilepic.setImageBitmap(bm);

            }
        }
    }
    private void storeImage(Bitmap thumbnail) {
        // Removing image saved earlier in shared prefernces
        PreferenceManager.getDefaultSharedPreferences(this).edit().clear().apply();
        createFolder();
        // this code is use to generate random number and add to file
        // name so that each file should be different
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";

        // set the file path
        // sdcard/PictureFolder/ is the folder created in create folder method
        String filePath = "/sdcard/PictureFolder/"+fname;
        // the rest of the code is for saving the file to filepath mentioned above
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

        //choose another format if PNG doesn't suit you
        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bos);
        shre =  getSharedPreferences("Profilepic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shre.edit();
        editor.putString(key,encodeTobase64(thumbnail));
        editor.commit();
        try {
            bos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            bos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;

    }
    public void createFolder()
    {
        // here PictureFolder is the folder name you can change it offcourse
        String RootDir = Environment.getExternalStorageDirectory()
                + File.separator + "PictureFolder";
        File RootFile = new File(RootDir);
        RootFile.mkdir();
    }


    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //@Override
        //public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

        // }
    }
}
