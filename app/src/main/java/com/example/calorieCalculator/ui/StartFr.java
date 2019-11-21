package com.example.calorieCalculator.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.calorieCalculator.Data.Loader;
import com.example.calorieCalculator.Data.ProductName;
import com.example.calorieCalculator.Data.ProductS;
import com.example.calorieCalculator.R;
import com.example.calorieCalculator.RecyclerView.DataAdapterAdd;
import com.example.calorieCalculator.RecyclerView.DataAdapterStart;

import java.util.ArrayList;
import java.util.List;

public class StartFr extends Fragment {
    Button add_btn;
    Button new_btn;
    Button start;
    AddFr addFr;
    DataAdapterStart adapterStart;
    TextView textViewResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_fr, null);

        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list_start);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);

        /******************** создаем адаптер *******************/
        List<ProductName> productArr = Loader.productList;
        adapterStart = new DataAdapterStart(inflater.getContext(), productArr);
        /**************** устанавливаем для списка адаптер **************/
        recyclerView.setAdapter(adapterStart);

        add_btn = (Button) v.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addFr = new AddFr();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, addFr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        new_btn = (Button) v.findViewById(R.id.new_btn);
        new_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Loader.init();
//                Loader.productList.get(i);

            }
        });

        start = (Button) v.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int result=0;
                for (int i = 0; Loader.productList.size() > i;i++ ){
                    result += (Loader.productList.get(i).getWeight()*Loader.productList.get(i).getCalorie()/100);
                }


                textViewResult.setText(result+" Ккал");

            }
        });





        return v;
    }







}