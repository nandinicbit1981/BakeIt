package vsp.shop.com.bakeit.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vsp.shop.com.bakeit.R;
import vsp.shop.com.bakeit.model.Ingredient;
import vsp.shop.com.bakeit.util.Constant;


//Remote Views Factory is a special adapter like class for widget

public class BakingWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int ID_CONSTANT = 0x0101010;

    private ArrayList<Ingredient> ingredients;
    private Context mContext;

    public BakingWidgetViewFactory(Context context, Intent intent) {
        mContext = context;
        try {
            String ingredients = intent.getExtras().get(Constant.INGREDIENTS).toString();
            this.ingredients = convertJsonToIngredientsList(new JSONArray(ingredients), mContext);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Ingredient> convertJsonToIngredientsList(JSONArray jsonArray, Context context) {
        ArrayList<Ingredient> bakeIngredientsList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Ingredient bakeIngredient = convertJsonToIngredients(jsonObject, context);
                bakeIngredientsList.add(bakeIngredient);

            }
        }catch (Exception e) {

        }
        return  bakeIngredientsList;
    }


    public static Ingredient convertJsonToIngredients(JSONObject jsonObject, Context context) {
        Ingredient ingredients= new Ingredient();
        try {
            Double quantity = Double.valueOf(jsonObject.get("quantity").toString());
            String measure = jsonObject.get("measure").toString();
            String ingredient = jsonObject.get("ingredient").toString();

            ingredients.setQuantity(quantity);
            ingredients.setMeasure(measure);
            ingredients.setIngredient(ingredient);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredients;

    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    //returns number of views in the collection widget
    //This works exactly the same as getCount() in an adapter and should be used to return the number of items in the collection.
    @Override
    public int getCount() {
        return ingredients.size();
    }

    // similar to getView method in an adapter, implements remote views instead of a regular view
    //getViewAt() method that works like getView() in an adapter. in this method,
    // we would create a new RemoteViews object that represents our row item,
    // setup any data that needs to be setup, and return the remote view. We can also setup our fill-in
    // intent here and attach it to the returned view.
    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.ingredients_detail_view);
        rv.setTextViewText(R.id.ingredients_name_txt_view, ingredients.get(i).getIngredient());
        rv.setTextViewText(R.id.ingredients_measurements_txt_view, ingredients.get(i).getQuantity() + " " +ingredients.get(i).getMeasure());
        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    // The getViewTypeCount() method returns the number of different view types used in the collection.
    // We'll only be using one view type, so make this method return 1.
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    //
    @Override
    public long getItemId(int i) {
        return ID_CONSTANT + i;
    }

    //Also, all of our row items are going to have stable IDs, so make hasStableIds() return true.
    @Override
    public boolean hasStableIds() {
        return true;
    }
}
