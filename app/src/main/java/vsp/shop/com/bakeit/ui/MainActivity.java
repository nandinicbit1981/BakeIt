package vsp.shop.com.bakeit.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.adapter.RecipeAdapter;
import vsp.shop.com.bakeit.model.Recipe;
import vsp.shop.com.bakeit.network.APIService;
import vsp.shop.com.bakeit.network.APIUtils;
import vsp.shop.com.bakeit.network.RetrofitClient;
import vsp.shop.com.bakeit.util.Constant;
import vsp.shop.com.bakeit.util.ExpressoIdlingResource;
import vsp.shop.com.bakeit.util.RecipeClickListener;

import static vsp.shop.com.bakeit.util.RecipeConstant.BROWNIES;
import static vsp.shop.com.bakeit.util.RecipeConstant.CHEESECAKE;
import static vsp.shop.com.bakeit.util.RecipeConstant.NUTELLA;
import static vsp.shop.com.bakeit.util.RecipeConstant.YELLOWCAKE;

public class MainActivity extends AppCompatActivity implements RecipeClickListener {

    private static final String TAG = MainActivity.class.getCanonicalName();
    APIService mApiService;
    List<Recipe> recipeList;
    RecipeAdapter recipeAdapter;

    @BindView(R.id.recipe_recycler_view)
    RecyclerView recipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recipeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recipeRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }
        getRecipes();
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getRecipes();
    }

    public void getRecipes() {
        mApiService = APIUtils.getAPIService();


        Call<List<Recipe>> response = RetrofitClient.Retrieve().getRecipe();
        ExpressoIdlingResource.increment();
        response.enqueue(new Callback<List<Recipe>>() {


            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipeList = response.body();
                ExpressoIdlingResource.decrement();
                for(Recipe recipe : recipeList) {
                    if(recipe.getName().toString().equals(NUTELLA)) {
                        recipe.setImage(String.valueOf(R.drawable.nutella));
                    } else if(recipe.getName().toString().equals(YELLOWCAKE)) {
                        recipe.setImage(String.valueOf(R.drawable.yellowcake));
                    } else if(recipe.getName().toString().equals(BROWNIES)) {
                        recipe.setImage(String.valueOf(R.drawable.brownies));
                    } else if(recipe.getName().toString().equals(CHEESECAKE)) {
                        recipe.setImage(String.valueOf(R.drawable.cheesecake));
                    }
                }
                recipeAdapter = new RecipeAdapter(recipeList, MainActivity.this, MainActivity.this);
                recipeRecyclerView.setAdapter(recipeAdapter);
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onRecipeClickListener(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constant.RECIPENAME, recipe.getName());
        intent.putParcelableArrayListExtra(Constant.INGREDIENTS, (ArrayList) recipe.getIngredients());
        intent.putParcelableArrayListExtra(Constant.STEPS, (ArrayList) recipe.getSteps());
        startActivity(intent);

    }

}

