package vsp.shop.com.bakeit.util;

import java.util.List;

import vsp.shop.com.bakeit.model.Ingredient;
import vsp.shop.com.bakeit.model.Step;

public interface StepClickListener {
    void onIngredientsClickListener(List<Ingredient> ingredients);
    void onStepsClickListener(List<Step> ingredients, int position);
}
