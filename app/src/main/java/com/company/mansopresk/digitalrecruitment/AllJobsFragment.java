package com.company.mansopresk.digitalrecruitment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class AllJobsFragment extends Fragment
{

    //RecyclerView recyclerView;

    public AllJobsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_all_jobs, container, false);


//        CustomAdapter customAdapter = new CustomAdapter(getContext());
//        recyclerView.setAdapter(customAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
