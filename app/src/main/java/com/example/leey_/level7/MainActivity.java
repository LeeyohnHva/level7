package com.example.leey_.level7;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<ApiJSON> recipes = new ArrayList<>();
    private ApiService mRecipeApiService;

    private PagerAdapter mSectionsPagerAdapter;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @Nullable
    @BindView(R.id.container)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
//        setSupportActionBar(mToolBar);
        requestData();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), recipes);
        mPager.setAdapter(mSectionsPagerAdapter);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<ApiJSON> recipes;

        public SectionsPagerAdapter(FragmentManager fm, List<ApiJSON> recipes) {
            super(fm);
            this.recipes = recipes;
        }

        @Override
        public Fragment getItem(int position) {

            return RecipeFragment.newInstance(recipes.get(position));
        }

        @Override
        public int getCount() {
            return recipes.size();
        }
    }

    private void requestData() {
        ApiService service = ApiService.retrofit.create(ApiService.class);
        Call<Recipes> call = service.getRecipe();
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                if (response.body() != null) {
                    recipes.addAll(Arrays.asList(response.body().recipes));
                    mSectionsPagerAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
            }

        });
    }
}
