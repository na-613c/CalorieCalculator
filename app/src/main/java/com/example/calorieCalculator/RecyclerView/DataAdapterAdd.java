package com.example.calorieCalculator.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calorieCalculator.Domain.GenerateProductList;
import com.example.calorieCalculator.Domain.ProductName;
import com.example.calorieCalculator.Domain.ProductS;
import com.example.calorieCalculator.R;

import java.util.List;


public class DataAdapterAdd extends RecyclerView.Adapter<DataAdapterAdd.ViewHolder> {

    private LayoutInflater inflater;
    private List<ProductS> productS;
    private boolean flag;
    private int active = Color.argb(50, 255, 253, 1);
    private int passive = Color.rgb(255, 255, 255);

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
        final ProductS product = productS.get(position);


        holder.name_add.setText(product.getName());
        holder.cal_add.setText(product.getCalorie() + " Ккал");


        final ProductName productName = new ProductName();

        holder.list_add_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flag = true;

                productName.setName(product.getName());
                productName.setCalorie(product.getCalorie());

                try {
                    productName.setWeight(Integer.parseInt(holder.editText.getText().toString()));
                } catch (Exception e) {
                    holder.editText.setText("0");
                    productName.setWeight(0);
                }

                productName.setFats(product.getFats());
                productName.setProtein(product.getProtein());
                productName.setCarbohydrates(product.getCarbohydrates());

                if (flag & productName.getWeight() != 0) {
                    GenerateProductList.productList.add(0, productName);
                    holder.list_add_1.setBackgroundColor(active);
                    flag = false;
                } else {
                    GenerateProductList.productList.remove(productName);
                    holder.list_add_1.setBackgroundColor(passive);
                    flag = true;
                }


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