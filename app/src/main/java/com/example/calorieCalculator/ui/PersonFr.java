package com.example.calorieCalculator.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

    private RadioButton radioBtnMan;
    private RadioButton radioBtnWoman;

    private RadioButton radioBtn1;
    private RadioButton radioBtn2;
    private RadioButton radioBtn3;
    private RadioButton radioBtn4;
    private RadioButton radioBtn5;


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


        radioBtnMan = (RadioButton) v.findViewById(R.id.radioButtonMan);
        radioBtnWoman = (RadioButton) v.findViewById(R.id.radioButtonWoman);

        radioBtn1 = (RadioButton) v.findViewById(R.id.activ1);
        radioBtn2 = (RadioButton) v.findViewById(R.id.activ2);
        radioBtn3 = (RadioButton) v.findViewById(R.id.activ3);
        radioBtn4 = (RadioButton) v.findViewById(R.id.activ4);
        radioBtn5 = (RadioButton) v.findViewById(R.id.activ5);


        height.setText(MainActivity.personObj.getHeight() + "");
        weight.setText(MainActivity.personObj.getWeight() + "");
        age.setText(MainActivity.personObj.getAge() + "");


        switch (MainActivity.personObj.getGender()) {
            case "man":
                radioBtnMan.setChecked(true);
                break;
            case "woman":
                radioBtnWoman.setChecked(true);
                break;
        }


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


        if (MainActivity.personObj.getActivity() == 1.2) {
            radioBtn1.setChecked(true);
        } else if (MainActivity.personObj.getActivity() == 1.375) {
            radioBtn2.setChecked(true);
        } else if (MainActivity.personObj.getActivity() == 1.55) {
            radioBtn3.setChecked(true);
        } else if (MainActivity.personObj.getActivity() == 1.725) {
            radioBtn4.setChecked(true);
        } else {
            radioBtn5.setChecked(true);
        }


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