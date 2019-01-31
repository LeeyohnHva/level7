package com.example.leey_.level7;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecipeFragment extends Fragment {

    private Unbinder unbinder;
    private static final String ARG_RECIPE_ID = "arg_recipe_id";
    private ApiJSON mData;


    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.text_view_recipe_name)
    TextView mTextViewRecipeName;
    @BindView(R.id.text_view_recipe_description)
    TextView mTextViewRecipeDescription;
    public RecipeFragment() {

    }


    public static RecipeFragment newInstance(ApiJSON apijson) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RECIPE_ID, apijson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getArguments().getParcelable(ARG_RECIPE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View recipeView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, recipeView);
        unbinder = ButterKnife.bind(this, recipeView);
        mTextViewRecipeName.setText(mData.getTitle());
        mTextViewRecipeDescription.setText(mData.getRecipeId());
        Glide.with(this)
                .load(mData.getImageUrl())
                .into(mImageView);


        return recipeView;}

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String loopThroughIngredients(List<String> ingredients) {
        StringBuilder ingredient = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredient.append("\t-\t").append(ingredients.get(i)).append("\n");
        }
        return ingredient.toString();
    }
}
