package vsp.shop.com.bakeit.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.model.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>{

    List<Ingredient> ingredients;
    Context context;

    public IngredientsAdapter(List<Ingredient> ingredients, Context context) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.ingredientName.setText(ingredients.get(position).getIngredient());
        holder.ingredientMeasure.setText(ingredients.get(position).getQuantity() + " " + ingredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientName;
        TextView ingredientMeasure;
        public IngredientsViewHolder(View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_item);
            ingredientMeasure = itemView.findViewById(R.id.ingredient_measure);
        }
    }
}
