package vsp.shop.com.bakeit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.adapter.DetailAdapter;
import vsp.shop.com.bakeit.model.Ingredient;
import vsp.shop.com.bakeit.model.Step;
import vsp.shop.com.bakeit.util.Constant;

public class DetailActivity extends AppCompatActivity {
    ArrayList<Ingredient> ingredientArrayList;
    ArrayList<Step> stepArrayList;

    @BindView(R.id.detail_rv)
    RecyclerView detailRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ingredientArrayList = getIntent().getExtras().getParcelableArrayList(Constant.INGREDIENTS);
        stepArrayList = getIntent().getExtras().getParcelableArrayList(Constant.STEPS);
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailRecyclerView.setAdapter(new DetailAdapter(this, ingredientArrayList, stepArrayList));
    }



}
