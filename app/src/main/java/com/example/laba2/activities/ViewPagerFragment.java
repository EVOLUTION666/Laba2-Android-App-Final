package com.example.laba2.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laba2.R;
import com.example.laba2.model.Civilization;

public class ViewPagerFragment extends Fragment {

    private String helpText;
    private String graphics;
    private Civilization item;

    public static ViewPagerFragment newInstance(String name)
    {
        Bundle args = new Bundle();
        args.putSerializable("itemArgs", Singleton.getInstance().getItem(name));
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = (Civilization) this.getArguments().getSerializable("itemArgs");
        helpText = item.getHelptext();
        graphics = item.getGraphic();
    }


    //Загружает отображение ViewPager'a
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_view_pager_fragment, container, false);
        ImageView imageView = v.findViewById(R.id.view_pager_image_view);
        TextView textView = v.findViewById(R.id.view_pager_text_view);

        Glide.with(getActivity()).load(graphics).into(imageView);
        textView.setText(helpText);

        return v;
    }
}
