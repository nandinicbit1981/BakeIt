package vsp.shop.com.bakeit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.adapter.DetailAdapter;
import vsp.shop.com.bakeit.model.Ingredient;
import vsp.shop.com.bakeit.model.Step;
import vsp.shop.com.bakeit.util.Constant;
import vsp.shop.com.bakeit.util.StepClickListener;
import vsp.shop.com.bakeit.widget.BakeWidget;

public class DetailActivity extends AppCompatActivity implements StepClickListener{
    ArrayList<Ingredient> ingredientArrayList;
    ArrayList<Step> stepArrayList;

    @BindView(R.id.detail_rv)
    RecyclerView detailRecyclerView;
    String recipeName;
    Boolean twoPane  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ingredientArrayList = getIntent().getExtras().getParcelableArrayList(Constant.INGREDIENTS);
        stepArrayList = getIntent().getExtras().getParcelableArrayList(Constant.STEPS);
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailRecyclerView.setAdapter(new DetailAdapter(this, ingredientArrayList, stepArrayList, this));
        recipeName = getIntent().getExtras().getString(Constant.RECIPENAME);
        if(findViewById(R.id.two_pane) != null) {
            twoPane = true;
        }

        Intent broadCastIntent = new Intent(getApplicationContext(), BakeWidget.class);
        broadCastIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        broadCastIntent.putExtra(Constant.INGREDIENTS, ingredientArrayList);
        broadCastIntent.putExtra(Constant.RECIPENAME,  recipeName);
        getApplicationContext().sendBroadcast(broadCastIntent);

        if(twoPane && savedInstanceState == null) {
            insertPlayerFragment(0);
        }
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
    }

    private void insertPlayerFragment(int stepIndex) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        PlayerFragment playerFragment = new PlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constant.STEPS, stepArrayList);
        bundle.putInt(Constant.CURRENTSTEP, stepIndex);
        playerFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.player_container, playerFragment)
                .commit();
    }


    @Override
    public void onIngredientsClickListener(List<Ingredient> ingredients) {

        if(twoPane) {
            IngredientFragment ingredientFragment = new IngredientFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constant.INGREDIENTS, (ArrayList) ingredients);
            ingredientFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.player_container, ingredientFragment)
                    .commit();
        } else {
            Intent intent = new Intent(DetailActivity.this, IngredientActivity.class);
            intent.putExtra(Constant.INGREDIENTS, ingredientArrayList);
            startActivity(intent);
        }
    }

    @Override
    public void onStepsClickListener(List<Step> steps, int position) {
        if(twoPane) {
            insertPlayerFragment(position);
        } else {
            Intent intent = new Intent(DetailActivity.this, StepsActivity.class);
            intent.putExtra(Constant.STEPS, stepArrayList);
            startActivity(intent);
        }
    }


}
