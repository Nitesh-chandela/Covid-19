package com.example.covid_19;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }
    Button symptoms;
    Button emergency;
    Button precautions;
    Button testing;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
      View info=inflater.inflate(R.layout.fragment_info, container, false);
       symptoms=info.findViewById(R.id.symptoms);
       emergency=info.findViewById(R.id.emergency);
       precautions=info.findViewById(R.id.precautions);
       testing=info.findViewById(R.id.testing);
         symptoms.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getContext(),InfoData.class);
                 intent.putExtra("getActivity","symptoms");
                 startActivity(intent);
             }
         });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),InfoData.class);
                intent.putExtra("getActivity","emergency");
                startActivity(intent);
            }
        });
        precautions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),InfoData.class);
                intent.putExtra("getActivity","precautions");
                startActivity(intent);
            }
        });

        testing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(getContext(),InfoData.class);
                    intent.putExtra("getActivity","testing");
                    startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return info;
    }

}
