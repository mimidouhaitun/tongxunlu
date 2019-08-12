package com.example.tarena.bmobdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tarena.bmobdemo.R;
import com.example.tarena.bmobdemo.adapter.ContactInfoAdapter;
import com.example.tarena.bmobdemo.bean.ContactInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.myToolBar)
    Toolbar myToolBar;
    @BindView(R.id.myRecycleViewID1)
    RecyclerView myRecycleViewID1;
    private List<ContactInfo> dataList = new ArrayList<>();
    private ContactInfoAdapter adapter;
    private Button toolBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        initToolBar();

        initRecycleView();

    }

    private void initToolBar() {
        myToolBar.setTitle("");
        setSupportActionBar(myToolBar);

        LinearLayout llToolBar= (LinearLayout) View.inflate(this,R.layout.toolbar_items,null);
        toolBack = llToolBar.findViewById(R.id.toolBack);
        TextView toolTitle=llToolBar.findViewById(R.id.toolTitle);
        toolTitle.setText("通讯信息表");
        toolBack.setOnClickListener(this);

        myToolBar.addView(llToolBar,new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT));
    }

    private void initRecycleView() {
        adapter = new ContactInfoAdapter();
        myRecycleViewID1.setHasFixedSize(true);
        myRecycleViewID1.setLayoutManager(new LinearLayoutManager(this));
        myRecycleViewID1.setAdapter(adapter);

        initData();
    }

    private void initData() {

        List<ContactInfo> list=new ArrayList<>();
        list.add(new ContactInfo("1111","男",35,"方正国际软件有限公司","软件开发工程师","已婚","15506136428"));
        list.add(new ContactInfo("2222","男",35,"方正国际软件有限公司","软件开发工程师","已婚","15506136428"));
        list.add(new ContactInfo("3333","男",35,"方正国际软件有限公司","软件开发工程师","已婚","15506136428"));
        list.add(new ContactInfo("4444","男",35,"方正国际软件有限公司","软件开发工程师","已婚","15506136428"));

        adapter.setDatas(list);

//        BmobQuery<ContactInfo> query = new BmobQuery<>();
//        query.findObjects(new FindListener<ContactInfo>() {
//            @Override
//            public void done(List<ContactInfo> list, BmobException e) {
//                if (e == null) {
//                    adapter.setDatas(list);
//                }
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
