package com.example.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Data.User;
import Data.UserSet;


public class Main2Activity extends AppCompatActivity {
private EditText username;
    private EditText userpwd;
private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findview();
        button1=(Button)findViewById(R.id.quit);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                Main2Activity.this.finish();
            }
        });

        button2=(Button)findViewById(R.id.register);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name=username.getText().toString().trim();
                String pwd=userpwd.getText().toString().trim();
                UserSet userSet=new UserSet(Main2Activity.this);
                User user =new User();
                user.setUsername(name);
                user.setPassword(pwd);
                userSet.register(user);
                new AlertDialog.Builder(Main2Activity.this)
                        .setMessage("注册成功，欢迎加入老司机的世界♪(＾∀＾●)ﾉ")
                        .setPositiveButton("确定",null)
                        .show();
}});
    }
    private void findview(){
        username=(EditText)findViewById(R.id.account);
        userpwd=(EditText)findViewById(R.id.pwd);
    }

}
