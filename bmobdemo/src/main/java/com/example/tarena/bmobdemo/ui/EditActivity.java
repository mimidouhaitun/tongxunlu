package com.example.tarena.bmobdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tarena.bmobdemo.R;
import com.example.tarena.bmobdemo.app.MyApp;
import com.example.tarena.bmobdemo.bean.ContactInfo;
import com.example.tarena.bmobdemo.util.ConstantData;
import com.example.tarena.bmobdemo.util.myUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class EditActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.rbMan)
    RadioButton rbMan;
    @BindView(R.id.rbLady)
    RadioButton rbLady;
    @BindView(R.id.rgGender)
    RadioGroup rgGender;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.etMobile)
    EditText etMobile;
    @BindView(R.id.rbMarryY)
    RadioButton rbMarryY;
    @BindView(R.id.rbMarryN)
    RadioButton rbMarryN;
    @BindView(R.id.rgMarry)
    RadioGroup rgMarry;
    @BindView(R.id.etChildCnt)
    EditText etChildCnt;
    @BindView(R.id.etCompanyName)
    EditText etCompanyName;
    @BindView(R.id.etCompanyAddr)
    EditText etCompanyAddr;
    @BindView(R.id.etPostName)
    EditText etPostName;
    @BindView(R.id.etHomeAddr)
    EditText etHomeAddr;
    @BindView(R.id.btnSave)
    Button btnSave;


    String Name;
    String Gender;
    int Age;
    String Mobile;
    String Marry;
    int ChildCnt;
    String CompanyName;
    String CompanyAddr;
    String PostName;//职位名称
    String HomeAddr;
    String objectID; //主键

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        initPage();
    }

    private void initPage() {

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("通讯录编辑");

        Intent intent = getIntent();
        String objectID = intent.getStringExtra(ConstantData.objectIdKey);
        if (TextUtils.isEmpty(objectID) == false) {
            BmobQuery<ContactInfo> query = new BmobQuery<>();
            query.getObject(objectID, new QueryListener<ContactInfo>() {
                @Override
                public void done(ContactInfo contactInfo, BmobException e) {
                    if (e == null) {
                        fillPage(contactInfo);
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSave)
    public void onSaveClick() {
        ContactInfo info = getPageInfo();
        if (info.checkIsNull()) {
            myUtil.showToast("信息填写不完整");
            return;
        }

        if (TextUtils.isEmpty(info.getObjectId())) {
            info.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        myUtil.showToast("保存成功");
                        Intent intent = new Intent(MyApp.CONTEXT, ListActivity.class);

                        startActivity(intent);
                        finish();
                    } else {
                        myUtil.showToast("保存失败" + e.toString());
                        Log.i("保存失败", e.toString());
                    }
                }
            });
        } else {
            info.update(objectID, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e != null) {
                        myUtil.showToast("修改成功");
                    } else {
                        myUtil.showToast("修改失败" + e.toString());
                        Log.i("修改失败", e.toString());
                    }
                }
            });
        }
    }

    @OnFocusChange(R.id.etName)
    public void nameFocusChange() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            return;
        }
        BmobQuery<ContactInfo> query = new BmobQuery<>();
        query.addWhereEqualTo("Name", etName.getText().toString());
        query.findObjects(new FindListener<ContactInfo>() {
            @Override
            public void done(List<ContactInfo> list, BmobException e) {
                if (list.size() > 0) {
                    ContactInfo info = list.get(0);
                    fillPage(info);
                } else {
                    objectID = "";
                }
            }
        });
    }

    //获得实体
    private ContactInfo getPageInfo() {
        Name = etName.getText().toString();

        RadioButton rbGender = findViewById(rgGender.getCheckedRadioButtonId());
        if (rbGender != null) {
            Gender = rbGender.getText().toString();
        }
        if (TextUtils.isEmpty(etAge.getText()) == false) {
            Age = Integer.parseInt(etAge.getText().toString());
        }
        Mobile = etMobile.getText().toString();

        RadioButton rbMarry = findViewById(rgMarry.getCheckedRadioButtonId());
        if (rbMarry != null) {
            Marry = rbMarry.getText().toString();
        }
        if (TextUtils.isEmpty(etChildCnt.getText()) == false) {
            ChildCnt = Integer.parseInt(etChildCnt.getText().toString());
        }
        CompanyName = etCompanyName.getText().toString();
        CompanyAddr = etCompanyAddr.getText().toString();
        PostName = etPostName.getText().toString();
        HomeAddr = etHomeAddr.getText().toString();

        ContactInfo info = new ContactInfo();
        info.setName(Name);
        info.setAge(Age);
        info.setGender(Gender);
        info.setMobile(Mobile);
        info.setMarry(Marry);
        info.setChildCnt(ChildCnt);
        info.setCompanyAddr(CompanyAddr);
        info.setCompanyName(CompanyName);
        info.setPostName(PostName);
        info.setHomeAddr(HomeAddr);

        info.setObjectId(objectID);

        return info;
    }

    private void fillPage(ContactInfo info) {
        etName.setText(info.getName());
        etAge.setText(info.getAge() + "");
        etChildCnt.setText(info.getChildCnt() + "");
        etCompanyAddr.setText(info.getCompanyAddr());
        etCompanyName.setText(info.getCompanyName());
        etHomeAddr.setText(info.getHomeAddr());
        etMobile.setText(info.getMobile());
        etHomeAddr.setText(info.getHomeAddr());
        etPostName.setText(info.getPostName());

        objectID = info.getObjectId();

        if (info.getGender().equals("男")) {
            rbMan.setChecked(true);
        } else {
            rbLady.setChecked(true);
        }

        if (info.getMarry().equals("已婚")) {
            rbMarryY.setChecked(true);
        } else {
            rbMarryN.setChecked(false);
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

}
