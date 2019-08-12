package com.example.tarena.bmobdemo.bean;

import android.text.TextUtils;

import cn.bmob.v3.BmobObject;

public class ContactInfo extends BmobObject {

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

    public ContactInfo(){
        super();
    }

    public ContactInfo(String name,String gender,int age,String companyName,String postName,String marry,String mobile) {
        this.Name = name;
        this.Gender=gender;
        this.Age=age;
        this.CompanyName=companyName;
        this.PostName=postName;
        this.Marry=marry;
        this.Mobile=mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getMarry() {
        return Marry;
    }

    public void setMarry(String marry) {
        Marry = marry;
    }

    public int getChildCnt() {
        return ChildCnt;
    }

    public void setChildCnt(int childCnt) {
        ChildCnt = childCnt;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddr() {
        return CompanyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        CompanyAddr = companyAddr;
    }

    public String getPostName() {
        return PostName;
    }

    public void setPostName(String postName) {
        PostName = postName;
    }

    public String getHomeAddr() {
        return HomeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        HomeAddr = homeAddr;
    }

    public Boolean checkIsNull() {
        if (TextUtils.isEmpty(getName()) ||
                TextUtils.isEmpty(getGender()) ||
                TextUtils.isEmpty(getMobile()) ||
                TextUtils.isEmpty(getMarry()) ||
                TextUtils.isEmpty(getCompanyName()) ||
                TextUtils.isEmpty(getCompanyAddr()) ||
                TextUtils.isEmpty(getPostName()) ||
                TextUtils.isEmpty(getHomeAddr())) {
            return true;
        }
        return false;
    }
}
