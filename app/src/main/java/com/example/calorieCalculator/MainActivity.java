package com.example.calorieCalculator;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calorieCalculator.Data.Loader;
import com.example.calorieCalculator.Data.PersonObj;
import com.example.calorieCalculator.ui.StartFr;

public class MainActivity extends AppCompatActivity {
    private StartFr startFr;
    private FragmentTransaction fTrans;
    static public PersonObj personObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Loader.init();

        personObj = new PersonObj();
        int ageInt = Integer.parseInt(getString(R.string.age));
        int heightInt = Integer.parseInt(getString(R.string.height));
        int weightInt = Integer.parseInt(getString(R.string.weight));

        personObj.setAge(ageInt);
        personObj.setHeight(heightInt);
        personObj.setWeight(weightInt);

        startFr = new StartFr();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.frgmCont, startFr);
        fTrans.commit();


    }
}
