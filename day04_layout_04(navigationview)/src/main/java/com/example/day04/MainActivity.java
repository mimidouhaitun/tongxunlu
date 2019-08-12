package com.example.day04;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);


        NavigationView navigationView =
                (NavigationView) findViewById(R.id.my_navigation_view);

        //处理navvigationview中headerview的点击事件
        View v=navigationView.getHeaderView(0);
        Log.i("TAG","v="+v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "headerview.v="+v, Toast.LENGTH_SHORT).show();
            }
        });

        //处理navigationView 中菜单点击事件
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(
                            @NonNull MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.item01) {
                            Toast.makeText(MainActivity.this, "item01", Toast.LENGTH_SHORT).show();
                        } else if (id == R.id.item02) {
                            //.....
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });

    }



}
