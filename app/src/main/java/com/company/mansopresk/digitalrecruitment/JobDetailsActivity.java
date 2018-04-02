package com.company.mansopresk.digitalrecruitment;

import android.app.Activity;
import android.os.Bundle;

public class JobDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        getActionBar().setDisplayHomeAsUpEnabled(true); // In `OnCreate();`
    }
    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }
}
