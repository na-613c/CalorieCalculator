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
import com.example.calorieCalculator.R;
import com.example.calorieCalculator.RecyclerView.DataAdapterStart;

import java.util.List;

public class StartFr extends Fragment {

    private Button add_btn;
    private Button new_btn;
    private Button updPerson;

    private PersonFr personFr;
    private AddFr addFr;
    private DataAdapterStart adapterStart;
    private CountCalForPerson countCalForPerson;

    private String allCal;

    private TextView textViewResult;
    private TextView fats;
    private TextView protein;
    private TextView carbohydrates;

    private int resultInt = 0;
    private int fatsInt = 0;
    private int proteinInt = 0;
    private int carbohydratesInt = 0;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_fr, null);

        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list_start);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);
        fats = (TextView) v.findViewById(R.id.fats);
        protein = (TextView) v.findViewById(R.id.protein);
        carbohydrates = (TextView) v.findViewById(R.id.carbohydrates);

        updPerson = (Button) v.findViewById(R.id.updPerson);
        add_btn = (Button) v.findViewById(R.id.add_btn);
        new_btn = (Button) v.findViewById(R.id.new_btn);

        countCalForPerson = new CountCalForPerson();
        allCal = countCalForPerson.countCal() + "";

        /******************** создаем адаптер *******************/
        List<ProductName> productArr = Loader.productList;
        adapterStart = new DataAdapterStart(inflater.getContext(), productArr);
        /**************** устанавливаем для списка адаптер **************/
        recyclerView.setAdapter(adapterStart);


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


        new_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Loader.init();
                adapterStart.clear();
                resultInt = 0;
                fatsInt = 0;
                proteinInt = 0;
                carbohydratesInt = 0;

                countCalForPerson = new CountCalForPerson();
                allCal = countCalForPerson.countCal() + "";

                StartSetText();
            }
        });

        for (int i = 0; productArr.size() > i; i++) {
            resultInt += (productArr.get(i).getCalorie() * productArr.get(i).getWeight() / 100);
            fatsInt += (productArr.get(i).getFats() * productArr.get(i).getWeight() / 100);
            proteinInt += (productArr.get(i).getProtein() * productArr.get(i).getWeight() / 100);
            carbohydratesInt += (productArr.get(i).getCarbohydrates() * productArr.get(i).getWeight() / 100);

        }

        if (resultInt == 0 & fatsInt == 0 & proteinInt == 0 & carbohydratesInt == 0) {
            StartSetText();
        } else {
            textViewResult.setText(resultInt + " из " + allCal + " Ккал");
            protein.setText("Бел " + proteinInt + "г, ");
            fats.setText("Жир " + fatsInt + "г, ");
            carbohydrates.setText("Угл " + carbohydratesInt + "г.");
        }

        return v;
    }

    private void StartSetText() {
        textViewResult.setText("Вам нужно " + allCal + " Ккал");
        fats.setText("");
        protein.setText("");
        carbohydrates.setText("");
    }

}