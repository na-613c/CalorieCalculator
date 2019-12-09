package com.example.calorieCalculator.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calorieCalculator.Domain.ProductName;
import com.example.calorieCalculator.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.calorieCalculator.ui.StartFr.writeInfo;


public class DataAdapterStart extends RecyclerView.Adapter<DataAdapterStart.ViewHolder> {

    private LayoutInflater inflater;
    private List<ProductName> productS;

    public DataAdapterStart(Context context, List<ProductName> productS) {
        this.productS = productS;
        this.inflater = LayoutInflater.from(context);
    }

    public void clear() {
        productS = new ArrayList<>();
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
        final ProductName product = productS.get(position);

        Log.d("____!_!_", "result " + product.getName() + " "
                + product.getCalorie() + " "
                + product.getWeight() + " ");

        holder.name_start.setText(product.getName());
        holder.cal_start.setText(product.getCalorie() + " Ккал");
        holder.weight_start.setText("Вес: " + product.getWeight() + "г");
        holder.fats_start.setText("Ж: " + product.getFats() + "г");
        holder.protein_start.setText("Б: " + product.getProtein() + "г");
        holder.carbohydrates_start.setText("У: " + product.getCarbohydrates() + "г");

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                productS.remove(product);
                notifyDataSetChanged();
                writeInfo();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productS.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView name_start, cal_start, weight_start, fats_start, protein_start, carbohydrates_start;
        final ImageButton imageButton;

        ViewHolder(View view) {
            super(view);
            name_start = (TextView) view.findViewById(R.id.name_start);
            cal_start = (TextView) view.findViewById(R.id.cal_start);
            weight_start = (TextView) view.findViewById(R.id.weight_start);
            fats_start = (TextView) view.findViewById(R.id.fats_start);
            protein_start = (TextView) view.findViewById(R.id.protein_start);
            carbohydrates_start = (TextView) view.findViewById(R.id.carbohydrates_start);
            imageButton = (ImageButton) view.findViewById(R.id.imageButton);
        }
    }
}