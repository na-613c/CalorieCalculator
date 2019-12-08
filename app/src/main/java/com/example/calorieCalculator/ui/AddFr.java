package com.example.calorieCalculator.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calorieCalculator.Data.ProductS;
import com.example.calorieCalculator.R;
import com.example.calorieCalculator.RecyclerView.DataAdapterAdd;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AddFr extends Fragment {

    private Button btn_start;
    private StartFr startFr;
    private Button btn_new;
    private UpdFr updFr;

    private List<ProductS> productArr = new ArrayList<>();
    private DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_fr, null);

        btn_start = (Button) v.findViewById(R.id.btn_start);
        btn_new = (Button) v.findViewById(R.id.btn_new);

        btn_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startFr = new StartFr();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, startFr)
                        .addToBackStack(null)
                        .commit();
            }
        });


        btn_new.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                updFr = new UpdFr();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.frgmCont, updFr)
                        .addToBackStack(null)
                        .commit();
            }
        });


        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.list_add);

        /******************** создаем адаптер *******************/
        final DataAdapterAdd adapterAdd = new DataAdapterAdd(inflater.getContext(), productArr);
        /**************** устанавливаем для списка адаптер **************/
        recyclerView.setAdapter(adapterAdd);

        /*************************** firebase ***************************/
        myRef = FirebaseDatabase.getInstance().getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @com.google.firebase.database.annotations.Nullable String s) {
                ProductS productFromDb = dataSnapshot.getValue(ProductS.class);

                assert productFromDb != null;

                productArr.add(0, productFromDb);

                adapterAdd.updateItems();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @com.google.firebase.database.annotations.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }


}