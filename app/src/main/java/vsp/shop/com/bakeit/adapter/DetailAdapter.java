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
import vsp.shop.com.bakeit.model.Step;
import vsp.shop.com.bakeit.util.Constant;
import vsp.shop.com.bakeit.util.StepClickListener;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    Context context;
    List<Ingredient> ingredients;
    List<Step> steps;
    StepClickListener stepClickListener;
    public DetailAdapter(Context context, List<Ingredient> ingredients, List<Step> steps, StepClickListener stepClickListener) {
        this.context = context;
        this.ingredients = ingredients;
        this.steps = steps;
        this.stepClickListener = stepClickListener;
    }
    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View itemView;
       DetailViewHolder vh;
        if (viewType == 0) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item, viewGroup, false);
            vh = new DetailViewHolder(itemView, viewType);
            return vh;
        }

        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item, viewGroup, false);
        vh = new DetailViewHolder(itemView, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, final int i) {
        if(detailViewHolder.getItemViewType() == 0 && i == 0) {
            detailViewHolder.stepDetail.setText(Constant.INGREDIENTS);
            detailViewHolder.stepDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   stepClickListener.onIngredientsClickListener(ingredients);
                }
            });
        } else {
            detailViewHolder.stepDetail.setText(steps.get(i-1).getShortDescription());
            detailViewHolder.stepDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stepClickListener.onStepsClickListener(steps, i - 1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return steps.size() + 1;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{
        TextView stepDetail;
        public DetailViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {
                stepDetail = (TextView) itemView.findViewById(R.id.detail_item);
            } else if (viewType == 1) {
                stepDetail = (TextView) itemView.findViewById(R.id.detail_item);
            }
        }
    }
}
