package vsp.shop.com.bakeit.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.model.Recipe;
import vsp.shop.com.bakeit.util.ClickListener;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    List<Recipe> recipeList;
    Context context;
    ClickListener clickListener;

    public RecipeAdapter(List<Recipe> recipes, Context context, ClickListener clickListener) {
        this.context = context;
        this.recipeList = recipes;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        holder.recipeItem.setBackground(context.getResources().getDrawable(Integer.valueOf(recipeList.get(position).getImage())));
        holder.textView.setText(recipeList.get(position).getName());
        holder.recipeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickListener(recipeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout recipeItem;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recipe_name);
            recipeItem = itemView.findViewById(R.id.recipe_item);
        }
    }
}
