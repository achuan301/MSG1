package com.example.hp.msg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {
    Button btn_ok;
    String APPKTY = "1081d53d52b30";
    String Appsecret = "6221401f098f4184642d3f9095e894b3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SMSSDK.initSDK(this, APPKTY, Appsecret);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        //判断结果是否已经完成
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //获取数据data
                            HashMap<String, Object> maps = (HashMap<String, Object>) data;
                            //获取国家信息
                            String country = (String) maps.get("country");
                            //获取手机号信息
                            String phone = (String) maps.get("phone");
                            submitUserInfo(country,phone);

                        }


                    }
                });
                registerPage.show(MainActivity.this);


            }
        });
    }

    public void submitUserInfo(String country, String phone) {
        Random r = new Random();
        String name = "hello world";
        String uid = Math.abs(r.nextInt()) + "";
        SMSSDK.submitUserInfo(uid, name, null, country, phone);

    }
}
