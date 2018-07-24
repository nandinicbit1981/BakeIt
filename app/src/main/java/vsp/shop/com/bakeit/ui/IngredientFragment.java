package vsp.shop.com.bakeit.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.adapter.IngredientsAdapter;
import vsp.shop.com.bakeit.model.Ingredient;
import vsp.shop.com.bakeit.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientFragment extends android.support.v4.app.Fragment {

    List<Ingredient> ingredients;

    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredientsRv;

    IngredientsAdapter ingredientsAdapter;
    public IngredientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ingredient, container, false);

        ButterKnife.bind(this, view );
        if(getArguments() != null) {
            ingredients = getArguments().getParcelableArrayList(Constant.INGREDIENTS);
        } else {
            ingredients = getActivity().getIntent().getExtras().getParcelableArrayList(Constant.INGREDIENTS);
        }
        ingredientsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientsAdapter = new IngredientsAdapter(ingredients, getActivity());
        ingredientsRv.setAdapter(ingredientsAdapter);

        return view;
    }

}
