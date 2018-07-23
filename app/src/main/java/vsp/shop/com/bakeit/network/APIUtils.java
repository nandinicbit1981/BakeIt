package vsp.shop.com.bakeit.network;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public static APIService getAPIService() {

        return null;
       // return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}