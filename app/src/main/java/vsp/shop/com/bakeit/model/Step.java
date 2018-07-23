
package vsp.shop.com.bakeit.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Step implements Parcelable {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private Long mId;
    @SerializedName("shortDescription")
    private String mShortDescription;
    @SerializedName("thumbnailURL")
    private String mThumbnailURL;
    @SerializedName("videoURL")
    private String mVideoURL;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        mShortDescription = shortDescription;
    }

    public String getThumbnailURL() {
        return mThumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        mThumbnailURL = thumbnailURL;
    }

    public String getVideoURL() {
        return mVideoURL;
    }

    public void setVideoURL(String videoURL) {
        mVideoURL = videoURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDescription);
        dest.writeValue(this.mId);
        dest.writeString(this.mShortDescription);
        dest.writeString(this.mThumbnailURL);
        dest.writeString(this.mVideoURL);
    }

    public Step() {
    }

    protected Step(Parcel in) {
        this.mDescription = in.readString();
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mShortDescription = in.readString();
        this.mThumbnailURL = in.readString();
        this.mVideoURL = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
