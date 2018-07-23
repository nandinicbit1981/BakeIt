
package vsp.shop.com.bakeit.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Ingredient implements Parcelable {

    @SerializedName("ingredient")
    private String mIngredient;
    @SerializedName("measure")
    private String mMeasure;
    @SerializedName("quantity")
    private Double mQuantity;

    public String getIngredient() {
        return mIngredient;
    }

    public void setIngredient(String ingredient) {
        mIngredient = ingredient;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public void setMeasure(String measure) {
        mMeasure = measure;
    }

    public Double getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Double quantity) {
        mQuantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mIngredient);
        dest.writeString(this.mMeasure);
        dest.writeValue(this.mQuantity);
    }

    public Ingredient() {
    }

    protected Ingredient(Parcel in) {
        this.mIngredient = in.readString();
        this.mMeasure = in.readString();
        this.mQuantity = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
