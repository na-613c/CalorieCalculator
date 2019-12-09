package com.example.calorieCalculator.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calorieCalculator.Domain.ProductS;
import com.example.calorieCalculator.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdFr extends Fragment {

    private DatabaseReference myRef;
    private Button add_btn_add;
    private AddFr addFr;
    private ProductS productS;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upd_fr, null);

        add_btn_add = (Button) v.findViewById(R.id.add_btn_add);

        editText1 = (EditText) v.findViewById(R.id.editText1);
        editText2 = (EditText) v.findViewById(R.id.editText2);
        editText3 = (EditText) v.findViewById(R.id.editText3);
        editText4 = (EditText) v.findViewById(R.id.editText4);
        editText5 = (EditText) v.findViewById(R.id.editText5);

        productS = new ProductS();

        add_btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (!editText5.getText().toString().equals("")) {
                    productS.setCarbohydrates(Integer.parseInt(editText5.getText().toString()));
                } else {
                    showToast("углеводы");
                }

                if (!editText4.getText().toString().equals("")) {
                    productS.setProtein(Integer.parseInt(editText4.getText().toString()));
                } else {
                    showToast("белки");
                }

                if (!editText3.getText().toString().equals("")) {
                    productS.setFats(Integer.parseInt(editText3.getText().toString()));
                } else {
                    showToast("жирность");
                }

                if (!editText2.getText().toString().equals("")) {
                    productS.setCalorie(Integer.parseInt(editText2.getText().toString()));
                } else {
                    showToast("калорийность");
                }

                if (!editText1.getText().toString().equals("")) {
                    productS.setName(editText1.getText().toString());
                } else {
                    showToast("название");
                }


                if (!editText1.getText().toString().equals("") &
                        !editText2.getText().toString().equals("") &
                        !editText3.getText().toString().equals("") &
                        !editText4.getText().toString().equals("") &
                        !editText5.getText().toString().equals("")) {

                    myRef = FirebaseDatabase.getInstance().getReference();
                    myRef.push().setValue(productS);


                    addFr = new AddFr();
                    getActivity().getFragmentManager().beginTransaction()
                            .replace(R.id.frgmCont, addFr)
                            .addToBackStack(null)
                            .commit();
                }


            }
        });
        return v;
    }

    private void showToast(String str) {
        Toast.makeText(getActivity(), "Укажите " + str, Toast.LENGTH_SHORT).show();
    }


}