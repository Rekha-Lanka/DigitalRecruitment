package com.company.mansopresk.digitalrecruitment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class ViewProfileActivity extends Activity {
SharedPreferences shre;
    EditText refid,stream,etname,dob,caddr,pin,phno,mobileno,email,paddr,institutespin,qualspin,applyspin,locspin;
    String srefid,sstream,sname,spwd,sdob,scaddr,spin,sphno,smobile,smail,spaddr,sinstitutespin,squalspin,sapplyspin,slocspin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        refid=findViewById(R.id.prefid);
        stream=findViewById(R.id.pstream);
        etname=findViewById(R.id.puname);
        dob=findViewById(R.id.pdob);
        caddr=findViewById(R.id.pcaddress);
        pin=findViewById(R.id.ppin);
        phno=findViewById(R.id.pphno);
        mobileno=findViewById(R.id.pmobileno);
        email=findViewById(R.id.pemail);
        paddr=findViewById(R.id.paddress);
        institutespin=findViewById(R.id.pinstitute);
        qualspin=findViewById(R.id.pqualification);
        applyspin=findViewById(R.id.paapply);
        locspin=findViewById(R.id.plocation);

        shre = getSharedPreferences("regdetails",MODE_PRIVATE);
        srefid = shre.getString("refid",null);
      //  editor.putString("rrefid",srefid);
        sstream=shre.getString("rstream",null);
        sname=shre.getString("rname",null);
        sdob=shre.getString("rdob",null);
        scaddr=shre.getString("rcaddress",null);
        spin=shre.getString("rpin",null);
        sphno=shre.getString("rphno",null);
        smobile=shre.getString("rmobile",null);
        smail=shre.getString("rmail",null);
        spaddr=shre.getString("rpaddr",null);
        sinstitutespin=shre.getString("rinstitutespin",null);
        sapplyspin=shre.getString("rapplyspin",null);
        slocspin=shre.getString("rlocationspin",null);
        squalspin=shre.getString("rqualspin",null);
        //stream,etname,dob,caddr,pin,phno,mobileno,email,paddr,institutespin,qualspin,applyspin,locspin
        refid.setText(srefid);
        stream.setText(sstream);
        etname.setText(sname);
        dob.setText(sdob);
        caddr.setText(scaddr);
        pin.setText(spin);
        phno.setText(sphno);
        mobileno.setText(smobile);
        email.setText(smail);
        paddr.setText(spaddr);
        institutespin.setText(sinstitutespin);
        applyspin.setText(sapplyspin);
        locspin.setText(slocspin);
        qualspin.setText(squalspin);


    }
}
