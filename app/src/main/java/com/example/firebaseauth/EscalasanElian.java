package com.example.firebaseauth;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class EscalasanElian extends AppCompatActivity {
    TextView textView2;
    TabLayout tabLayout;
    ViewPager2 Pager2;
    Fragmentadapter adapter;
    String rutdelpaciente, llave = "rutdelpaciente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevasanelian);
        Bundle bundle = getIntent().getExtras();
        tabLayout = findViewById(R.id.tab_layout); //https://www.youtube.com/watch?v=5-RWOvJ9oq8&ab_channel=Code2Develop
        Pager2 = findViewById(R.id.viewpager2);
        TextView textinfo;
        textinfo = findViewById(R.id.textView11);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new Fragmentadapter(fm, getLifecycle());
        Pager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Localización"));
        tabLayout.addTab(tabLayout.newTab().setText("Estado"));
        tabLayout.addTab(tabLayout.newTab().setText("Dimensión"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Pager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //textView2=findViewById(R.id.textView2);
        //rutdelpaciente = bundle.getString(llave);
        //textView2.setText(rutdelpaciente);



    }

    public void informaciondeescala(View view) {
        TextView textinfo;
        textinfo = findViewById(R.id.textView11);
        textinfo.setVisibility(View.VISIBLE);
    }
}