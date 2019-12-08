package com.example.calorieCalculator.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.calorieCalculator.MainActivity;
import com.example.calorieCalculator.R;

public class PersonFr extends Fragment {

    private EditText height;
    private EditText weight;
    private EditText age;

    private StartFr startFr;

    private Button save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.person_fr, null);
        height = (EditText) v.findViewById(R.id.height);
        weight = (EditText) v.findViewById(R.id.weight);
        age = (EditText) v.findViewById(R.id.age);
        save = (Button) v.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ageInt = 0;
                int heightInt = 0;
                int weightInt = 0;
                try {
                    ageInt = Integer.parseInt(String.valueOf(age.getText()));
                    heightInt = Integer.parseInt(String.valueOf(height.getText()));
                    weightInt = Integer.parseInt(String.valueOf(weight.getText()));
                } catch (Exception e) {

                }


                MainActivity.personObj.setHeight(heightInt);
                MainActivity.personObj.setAge(ageInt);
                MainActivity.personObj.setWeight(weightInt);

                startFr = new StartFr();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, startFr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }


}