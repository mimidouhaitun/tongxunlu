package com.example.tarena.bmobdemo.bean;

import cn.bmob.v3.BmobObject;

public class ContactUser extends BmobObject {
    String phone;
    String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
