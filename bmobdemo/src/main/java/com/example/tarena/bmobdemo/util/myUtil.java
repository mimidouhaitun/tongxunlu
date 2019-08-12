package com.example.tarena.bmobdemo.util;

import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.widget.Toast;

import com.example.tarena.bmobdemo.app.MyApp;

public class myUtil {

    //判断手机号是否正确
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "^((1[3,5,7,8][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    public static void showToast(String mess){
        Toast toast = Toast.makeText(MyApp.CONTEXT, mess, Toast.LENGTH_LONG);
        // 这里给了一个1/4屏幕高度的y轴偏移量
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
