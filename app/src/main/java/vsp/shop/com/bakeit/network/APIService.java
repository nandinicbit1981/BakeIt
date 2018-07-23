package vsp.shop.com.bakeit.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vsp.shop.com.bakeit.model.Recipe;

public interface APIService {

    @GET("baking.json")
    Call<List<Recipe>> getRecipe();

}
