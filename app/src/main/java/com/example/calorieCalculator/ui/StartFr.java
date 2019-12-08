package com.example.calorieCalculator.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.calorieCalculator.CountCalForPerson;
import com.example.calorieCalculator.Data.Loader;
import com.example.calorieCalculator.Data.ProductName;
import com.example.calorieCalculator.MainActivity;
import com.example.calorieCalculator.R;
import com.example.calorieCalculator.RecyclerView.DataAdapterStart;

import java.util.List;

public class StartFr extends Fragment {
    Button add_btn;
    Button new_btn;
    Button updPerson;

    PersonFr personFr;
    AddFr addFr;
    DataAdapterStart adapterStart;
    CountCalForPerson countCalForPerson;

    String allCal;

    TextView textViewResult;
    int result = 0;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_fr, null);

        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list_start);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);

        updPerson = (Button) v.findViewById(R.id.updPerson);
        add_btn = (Button) v.findViewById(R.id.add_btn);
        new_btn = (Button) v.findViewById(R.id.new_btn);

        /******************** создаем адаптер *******************/
        List<ProductName> productArr = Loader.productList;
        adapterStart = new DataAdapterStart(inflater.getContext(), productArr);
        /**************** устанавливаем для списка адаптер **************/
        recyclerView.setAdapter(adapterStart);


//
//        int ageInt = Integer.parseInt(String.valueOf(R.string.age));
//        int heightInt = Integer.parseInt(String.valueOf(R.string.height));
//        int weightInt = Integer.parseInt(String.valueOf(R.string.weight));
//
//        MainActivity.personObj.setAge(ageInt);
//        MainActivity.personObj.setHeight(heightInt);
//        MainActivity.personObj.setWeight(weightInt);


        add_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFr = new AddFr();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, addFr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        updPerson.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                personFr = new PersonFr();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, personFr)
                        .addToBackStack(null)
                        .commit();
            }
        });


        countCalForPerson = new CountCalForPerson();
        allCal = countCalForPerson.countCal() + "";

        new_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Loader.init();
                adapterStart.clear();
                result = 0;

                countCalForPerson = new CountCalForPerson();
                allCal = countCalForPerson.countCal() + "";

                textViewResult.setText(result + " из " + allCal + " Ккал");
            }
        });


        for (int i = 0; productArr.size() > i; i++) {
            result += (productArr.get(i).getWeight() * productArr.get(i).getCalorie() / 100);
        }


        textViewResult.setText(result + " из " + allCal + " Ккал");


        return v;
    }

}