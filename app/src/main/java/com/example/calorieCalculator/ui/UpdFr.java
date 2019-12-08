package com.example.calorieCalculator.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.calorieCalculator.Data.ProductS;
import com.example.calorieCalculator.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdFr extends Fragment {

    private DatabaseReference myRef;
    Button add_btn_add;
    AddFr addFr;
    ProductS productS;
    EditText editText1;
    EditText editText2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.upd_fr, null);


        add_btn_add = (Button) v.findViewById(R.id.add_btn_add);
        editText1 = (EditText) v.findViewById(R.id.editText1);
        editText2 = (EditText) v.findViewById(R.id.editText2);

        add_btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                productS = new ProductS();
                productS.setName(editText1.getText().toString());
                productS.setCalorie(Integer.parseInt(editText2.getText().toString()));


                myRef = FirebaseDatabase.getInstance().getReference();
                myRef.push().setValue(productS);


                addFr = new AddFr();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, addFr)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }


}