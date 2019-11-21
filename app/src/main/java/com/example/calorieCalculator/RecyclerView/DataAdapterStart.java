package com.example.calorieCalculator.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.List;


public class DataAdapterStart extends RecyclerView.Adapter<DataAdapterStart.ViewHolder> {

    private LayoutInflater inflater;
    private List<ProductName> productS;

    public DataAdapterStart(Context context, List<ProductName> productS) {
        this.productS = productS;
        this.inflater = LayoutInflater.from(context);
    }

    public void updateItems() {
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DataAdapterStart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_start, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final DataAdapterStart.ViewHolder holder, int position) {
        ProductName product = productS.get(position);

        Log.d("____!_!_","result "+product.getName()+ " "
                + product.getCalorie()+" "
                +product.getWeight()+" ");

        holder.name_start.setText(product.getName());
        holder.cal_start.setText(product.getCalorie()+"");
        holder.weight_start.setText(product.getWeight()+"");

    }

    @Override
    public int getItemCount() {
        return productS.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView name_start, cal_start,weight_start;


        ViewHolder(View view) {
            super(view);
            name_start = (TextView) view.findViewById(R.id.name_start);
            cal_start = (TextView) view.findViewById(R.id.cal_start);
            weight_start = (TextView) view.findViewById(R.id.weight_start);


        }
    }
}