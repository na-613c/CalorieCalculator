package com.example.calorieCalculator.ui;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.calorieCalculator.CountCalForPerson;
import com.example.calorieCalculator.Domain.GenerateProductList;
import com.example.calorieCalculator.Domain.ProductName;
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

    private static int allCal;

    private static TextView textViewResult;
    private static TextView fats;
    private static TextView protein;
    private static TextView carbohydrates;

    private static int resultInt = 0;
    private static int fatsInt = 0;
    private static int proteinInt = 0;
    private static int carbohydratesInt = 0;
    private static int black = Color.rgb(0, 0, 0);
    private static int red = Color.rgb(255, 0, 0);
    private static int green = Color.rgb(0, 155, 0);


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
        allCal = countCalForPerson.countCal();

        /******************** создаем адаптер *******************/
        List<ProductName> productArr = GenerateProductList.productList;
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

                GenerateProductList.init();
                adapterStart.clear();
                resultInt = 0;
                fatsInt = 0;
                proteinInt = 0;
                carbohydratesInt = 0;

                countCalForPerson = new CountCalForPerson();
                allCal = countCalForPerson.countCal();

                StartSetText();
            }
        });

        writeInfo();

        return v;
    }

    private static void StartSetText() {


        textViewResult.setText("Вам нужно " + allCal + " Ккал");
        textViewResult.setTextColor(black);
        fats.setText("");
        protein.setText("");
        carbohydrates.setText("");
    }

    public static void writeInfo() {

        resultInt = 0;
        fatsInt = 0;
        proteinInt = 0;
        carbohydratesInt = 0;

        for (int i = 0; GenerateProductList.productList.size() > i; i++) {
            resultInt += (GenerateProductList.productList.get(i).getCalorie() * GenerateProductList.productList.get(i).getWeight() / 100);
            fatsInt += (GenerateProductList.productList.get(i).getFats() * GenerateProductList.productList.get(i).getWeight() / 100);
            proteinInt += (GenerateProductList.productList.get(i).getProtein() * GenerateProductList.productList.get(i).getWeight() / 100);
            carbohydratesInt += (GenerateProductList.productList.get(i).getCarbohydrates() * GenerateProductList.productList.get(i).getWeight() / 100);

        }

        if (resultInt == 0 & fatsInt == 0 & proteinInt == 0 & carbohydratesInt == 0) {
            StartSetText();
        } else {
            if (resultInt - 200 > allCal | resultInt + 200 < allCal) {
                textViewResult.setTextColor(red);

            } else {
                textViewResult.setTextColor(green);
            }
            textViewResult.setText(resultInt + " из " + allCal + " Ккал");
            protein.setText("Бел " + proteinInt + "г, ");
            fats.setText("Жир " + fatsInt + "г, ");
            carbohydrates.setText("Угл " + carbohydratesInt + "г.");
        }
    }

}