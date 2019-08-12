package com.example.tarena.bmobdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarena.bmobdemo.R;
import com.example.tarena.bmobdemo.app.MyApp;
import com.example.tarena.bmobdemo.bean.ContactUser;
import com.example.tarena.bmobdemo.util.myUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class ZhuceActivity extends Activity {

    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etPwd2)
    EditText etPwd2;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    private String phone;
    private String pwd;
    private  String pwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnRegister)
    public void onClick() {
        phone=etPhone.getText().toString();
        pwd=etPwd.getText().toString();
        pwd2=etPwd2.getText().toString();

        if(myUtil.isMobileNO(phone)==false){
            myUtil.showToast("手机号不正确");
            return;
        }

        ContactUser user=new ContactUser();
        user.setPhone(phone);
        user.setPwd(pwd);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    myUtil.showToast("注册成功");
                    Intent intent=new Intent(MyApp.CONTEXT,LoginActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("pwd",pwd);
                    startActivity(intent);
                    finish();
                }else{
                    myUtil.showToast("注册失败");
                    Log.i("注册失败",e.toString());
                }
            }
        });
    }
}
