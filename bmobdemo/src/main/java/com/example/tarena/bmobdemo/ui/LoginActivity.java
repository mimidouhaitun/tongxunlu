package com.example.tarena.bmobdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tarena.bmobdemo.R;
import com.example.tarena.bmobdemo.app.MyApp;
import com.example.tarena.bmobdemo.bean.ContactUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.llBtn)
    LinearLayout llBtn;

    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initPage();

    }

    private void initPage(){
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        pwd=intent.getStringExtra("pwd");

        if(TextUtils.isEmpty(phone)==false)
            etPhone.setText(phone);

        if(TextUtils.isEmpty(pwd)==false)
            etPwd.setText(pwd);
    }


    @OnClick({R.id.btnLogin, R.id.btnRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                phone=etPhone.getText().toString();
                pwd=etPwd.getText().toString();

                BmobQuery<ContactUser> query=new BmobQuery<>();
                query.addWhereEqualTo("phone",phone);
                query.addWhereEqualTo("pwd",pwd);
                query.findObjects(new FindListener<ContactUser>() {
                    @Override
                    public void done(List<ContactUser> list, BmobException e) {
                        if (e==null){
                            if(list.size()==0){
                                Toast.makeText(MyApp.CONTEXT,"用户名或密码出错",Toast.LENGTH_LONG).show();
                                return;
                            }else {
                                Intent intent1 = new Intent(LoginActivity.this, ListActivity.class);
                                startActivity(intent1);
                                return;
                            }
                        }else{
                            Toast.makeText(MyApp.CONTEXT,"查询出错"+e.toString(),Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
                break;
            case R.id.btnRegister:
                Intent intent2 = new Intent(LoginActivity.this, ZhuceActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
