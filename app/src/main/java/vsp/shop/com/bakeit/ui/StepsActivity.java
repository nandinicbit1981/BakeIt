package vsp.shop.com.bakeit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vsp.shop.com.bakeit.R;

public class StepsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
    }
}
