package vsp.shop.com.bakeit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.adapter.IngredientsAdapter;
import vsp.shop.com.bakeit.model.Ingredient;
import vsp.shop.com.bakeit.util.Constant;

public class IngredientActivity extends AppCompatActivity {

    List<Ingredient> ingredients;

    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredientsRv;

    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        ButterKnife.bind(this);
        ingredients = getIntent().getExtras().getParcelableArrayList(Constant.INGREDIENTS);
        ingredientsRv.setLayoutManager(new LinearLayoutManager(this));
        ingredientsAdapter = new IngredientsAdapter(ingredients, this);
        ingredientsRv.setAdapter(ingredientsAdapter);

    }
}
