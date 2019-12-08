package com.example.calorieCalculator;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.calorieCalculator.Data.Loader;
import com.example.calorieCalculator.Data.PersonObj;
import com.example.calorieCalculator.ui.AddFr;
import com.example.calorieCalculator.ui.StartFr;

public class MainActivity extends AppCompatActivity {
    StartFr startFr;
    FragmentTransaction fTrans;
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

        Log.d(" ERR", getString(R.string.age));
        Log.d(" ERR", getString(R.string.height));
        Log.d(" ERR", getString(R.string.weight));

        personObj.setAge(ageInt);
        personObj.setHeight(heightInt);
        personObj.setWeight(weightInt);

        startFr = new StartFr();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.frgmCont, startFr);
        fTrans.commit();


    }
}
