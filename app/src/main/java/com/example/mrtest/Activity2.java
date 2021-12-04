package com.example.mrtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Activity2 extends AppCompatActivity {

    private List<BackGround> list=new ArrayList<>();
    ViewPager2 mViewPager2;

    TextView textView;

    private int a=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        initImg();

        mViewPager2=findViewById(R.id.view_pager);
        NewAdapter newAdapter=new NewAdapter(list);
        mViewPager2.setAdapter(newAdapter);

        textView=findViewById(R.id.text_view4);

        textView.setText("不准点我");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a==4){
                    a=1;
                }
              if(a==1){
                  textView.setText("你再敢点试试？！");
                  replaceFragment(new FirstFragment());
                  ++a;
              }  else if(a==2){
                  textView.setText("让你点你还真点。。。");
                  replaceFragment(new SecondFragment());
                  ++a;
              }else{
                  replaceFragment(new nullFragment());
                  ++a;
              }
            }



        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                View decorView = window.getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                attributes.flags |= flagTranslucentStatus;
                window.setAttributes(attributes);
            }
        }



        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();


        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        //实现侧滑
        Toolbar toolbar =findViewById(R.id.TEST4);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {


                View content = drawerLayout.getChildAt(0);

                View menu = drawerView.findViewById(R.id.content_frame);

                float scale = 1 - slideOffset;
                float leftScale = (float) (1 - 0.3 * scale);
                float rightScale = (float) (0.7f + 0.3 * scale);

                menu.setScaleX(leftScale);
                content.setScaleY(rightScale);
                content.setTranslationX(menu.getMeasuredWidth() * slideOffset);

                content.setAlpha(1-0.6f * (1 - scale));
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }


    public void initImg(){

        BackGround backGround1=new BackGround(R.drawable.test10);
        list.add(backGround1);
        BackGround backGround2=new BackGround(R.drawable.test11);
        list.add(backGround2);
        BackGround backGround3=new BackGround(R.drawable.test12);
        list.add(backGround3);
        BackGround backGround4=new BackGround(R.drawable.test13);
        list.add(backGround4);
        BackGround backGround5=new BackGround(R.drawable.test14);
        list.add(backGround5);

    }

}