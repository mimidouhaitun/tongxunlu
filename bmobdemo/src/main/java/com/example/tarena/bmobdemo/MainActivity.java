package com.example.tarena.bmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tarena.bmobdemo.app.MyApp;
import com.example.tarena.bmobdemo.ui.EditActivity;
import com.example.tarena.bmobdemo.ui.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.myNavigationViewID)
    NavigationView myNavigationViewID;
    @BindView(R.id.myDrawer)
    DrawerLayout myDrawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
        initActionBar();
    }

    private void init() {
        myNavigationViewID.setNavigationItemSelectedListener(this);
    }
    private void initActionBar(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this,myDrawer,R.string.Open,R.string.Close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(R.string.Close);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                setTitle(R.string.Open);
            }
        };

        myDrawer.addDrawerListener(toggle);

        setTitle(R.string.Open);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.changePwd) {

        } else if (id == R.id.editInfo) {
            Intent intent=new Intent(MyApp.CONTEXT,EditActivity.class);
            startActivity(intent);
        } else if (id == R.id.viewList) {
            Intent intent=new Intent(MyApp.CONTEXT,ListActivity.class);
            startActivity(intent);
        }
        myDrawer.closeDrawer(Gravity.START);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean flag=super.onPrepareOptionsMenu(menu);
        toggle.syncState();
        return flag;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

}
