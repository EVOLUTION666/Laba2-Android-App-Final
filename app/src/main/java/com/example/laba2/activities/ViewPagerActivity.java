package com.example.laba2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.laba2.R;
import com.example.laba2.model.Civilization;

import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Civilization> items;
    private int name;


    //Находими ViewPager по Id и настраиваем адаптер для ViewPager
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.activity_item_view_pager);

        name = this.getIntent().getIntExtra("name", 0);

        items = Singleton.getInstance().getItems();

        FragmentManager fm = getSupportFragmentManager();

        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Civilization civ = items.get(position);
                //Создает новый фрагмент, в который передает имя из объекта Civilization
                return ViewPagerFragment.newInstance(civ.getName());
            }

            @Override
            public int getCount() {
                return items.size();
            }
        });

        viewPager.setCurrentItem(name);


    }
}
