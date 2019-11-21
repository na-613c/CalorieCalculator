package com.example.calorieCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.calorieCalculator.Data.Loader;
import com.example.calorieCalculator.ui.AddFr;
import com.example.calorieCalculator.ui.StartFr;

public class MainActivity extends AppCompatActivity {
    StartFr startFr;
    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Loader.init();

        startFr = new StartFr();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.frgmCont, startFr);
        fTrans.commit();


    }
}
