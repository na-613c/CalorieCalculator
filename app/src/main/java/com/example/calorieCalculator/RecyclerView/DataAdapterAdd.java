package com.example.calorieCalculator.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calorieCalculator.Data.Loader;
import com.example.calorieCalculator.Data.ProductName;
import com.example.calorieCalculator.Data.ProductS;
import com.example.calorieCalculator.R;
import com.example.calorieCalculator.ui.StartFr;


import java.util.ArrayList;
import java.util.List;

import static com.example.calorieCalculator.Data.Loader.productList;


public class DataAdapterAdd extends RecyclerView.Adapter<DataAdapterAdd.ViewHolder> {

    private LayoutInflater inflater;
    private List<ProductS> productS;

    public DataAdapterAdd(Context context, List<ProductS> productS) {
        this.productS = productS;
        this.inflater = LayoutInflater.from(context);
    }

    public void updateItems() {
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DataAdapterAdd.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_add, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final DataAdapterAdd.ViewHolder holder, int position) {
        ProductS product = productS.get(position);

        holder.name_add.setText(product.getName());
        holder.cal_add.setText(Integer.toString(product.getCalorie()));

        holder.list_add_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                ProductName productName = new ProductName();

                productName.setName(holder.name_add.getText().toString());
                productName.setCalorie(Integer.parseInt(holder.cal_add.getText().toString()));
                try{
                    productName.setWeight(Integer.parseInt(holder.editText.getText().toString()));
                }catch (Exception e){
                    holder.editText.setText("0");
                    productName.setWeight(0);
                }

                Loader.productList.add(productName);
                Log.d("__productList_",""+productList.get(0).getName());

                int backColor = Color.rgb(100,255,0);
                holder.list_add_1.setBackgroundColor(backColor);

            }
        });




    }

    @Override
    public int getItemCount() {
        return productS.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView name_add, cal_add;
        final LinearLayout list_add_1;
        final EditText editText;

        ViewHolder(View view) {
            super(view);
            name_add = (TextView) view.findViewById(R.id.name_add);
            cal_add = (TextView) view.findViewById(R.id.cal_add);
            editText = (EditText) view.findViewById(R.id.editText);
            list_add_1 = (LinearLayout) view.findViewById(R.id.list_add_1);

        }
    }
}