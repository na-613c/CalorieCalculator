package com.example.calorieCalculator.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.calorieCalculator.MainActivity;
import com.example.calorieCalculator.R;

public class PersonFr extends Fragment {

    private EditText height;
    private EditText weight;
    private EditText age;

    private StartFr startFr;

    private Button save;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.person_fr, null);
        height = (EditText) v.findViewById(R.id.height);
        weight = (EditText) v.findViewById(R.id.weight);
        age = (EditText) v.findViewById(R.id.age);
        save = (Button) v.findViewById(R.id.save);

        radioGroup1 = (RadioGroup) v.findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup) v.findViewById(R.id.radioGroup2);


        height.setText(MainActivity.personObj.getHeight() + "");
        weight.setText(MainActivity.personObj.getWeight() + "");
        age.setText(MainActivity.personObj.getAge() + "");

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonMan:
                        MainActivity.personObj.setGender("man");
                        break;
                    case R.id.radioButtonWoman:
                        MainActivity.personObj.setGender("woman");
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.activ1:
                        MainActivity.personObj.setActivity(1.2);
                        break;
                    case R.id.activ2:
                        MainActivity.personObj.setActivity(1.375);
                        break;
                    case R.id.activ3:
                        MainActivity.personObj.setActivity(1.55);
                        break;
                    case R.id.activ4:
                        MainActivity.personObj.setActivity(1.725);
                        break;
                    case R.id.activ5:
                        MainActivity.personObj.setActivity(1.9);
                        break;
                }
            }
        });


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