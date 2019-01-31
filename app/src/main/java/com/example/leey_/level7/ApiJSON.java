package com.example.leey_.level7;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiJSON implements Parcelable {

    @SerializedName("ingredients")
    @Expose
    private List<String> ingredients = null;
    @SerializedName("recipe_id")
    @Expose
    private String recipeId;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("title")
    @Expose
    private String title;



    protected ApiJSON(Parcel in) {
        this.ingredients = in.createStringArrayList();
        this.recipeId = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
    }

    public String getRecipeId() {
        return recipeId;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.ingredients);
        dest.writeString(this.recipeId);
        dest.writeString(this.imageUrl);
        dest.writeString(this.title);
    }

    public static final Creator<ApiJSON> CREATOR = new Creator<ApiJSON>() {
        @Override
        public ApiJSON createFromParcel(Parcel source) {
            return new ApiJSON(source);
        }

        @Override
        public ApiJSON[] newArray(int size) {
            return new ApiJSON[size];
        }
    };
}
