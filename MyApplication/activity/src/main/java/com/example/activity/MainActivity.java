package com.example.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import Data.UserSet;


public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText userpwd;
    private Button button1,button2,button3;
    private CheckBox checkBox,checkBox2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox=(CheckBox)findViewById(R.id.remember) ;
        checkBox2=(CheckBox)findViewById(R.id.autologin) ;
        button1=(Button)findViewById(R.id.register);
        button2=(Button)findViewById(R.id.login);
        button3=(Button)findViewById(R.id.runoff);
        username=(EditText)findViewById(R.id.account);
        userpwd=(EditText)findViewById(R.id.pwd);
        SharedPreferences sharedPreferences=getSharedPreferences("user",MainActivity.MODE_PRIVATE);
       checkBox.setChecked(sharedPreferences.getBoolean("chk",false));
        checkBox2.setChecked(sharedPreferences.getBoolean("autologin",false));
       if (checkBox2.isChecked()){

           new AlertDialog.Builder(MainActivity.this)
           .setMessage("自动登录成功,欢迎进入老司机的世界↖(^ω^)↗")
                   .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                       @Override
                       public void onClick(DialogInterface dialogInterface,int which){
                           Intent intent=new Intent(MainActivity.this,Main4Activity.class);
                           startActivity(intent);
                           MainActivity.this.finish();
                       }
                   })
                   .show();
       }
       if (checkBox.isChecked()){
            username.setText(sharedPreferences.getString("username",""));
            userpwd.setText(sharedPreferences.getString("userpwd",""));
        }
        findview();
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pwd=userpwd.getText().toString();
                UserSet userSet=new UserSet(MainActivity.this);
                boolean flag=userSet.login(name,pwd);
                if(flag){
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("成功了,欢迎进入老司机的世界^_^")
                            .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface,int which){
                                Intent intent=new Intent(MainActivity.this,Main4Activity.class);
                                    startActivity(intent);
                                    MainActivity.this.finish();
                                }
                            })
                            .show();
                    if(checkBox.isChecked()){
                     keep();
                          if (checkBox2.isChecked()){
                              keep_autologin();
                          }
                    }
                }else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("失败了")
                            .setMessage("登录失败了呢,自己找找错误原因把(┬＿┬)")
                            .setPositiveButton("返回",null)
                            .show();
                }
            }});
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
    public void onStop(){
        super.onStop();
        if (checkBox.isChecked()){}
        else {
            delete();
        }
    }
    public void findview(){
        username=(EditText)findViewById(R.id.account);
        userpwd=(EditText)findViewById(R.id.pwd);
    }
    public void keep(){
        SharedPreferences sharedPreferences =getSharedPreferences("user",MainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",username.getText().toString());
        editor.putString("userpwd",userpwd.getText().toString());
        editor.putBoolean("chk",checkBox.isChecked());
        editor.commit();
    }
    public void keep_autologin(){
        SharedPreferences sharedPreferences=getSharedPreferences("user",MainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("autologin",checkBox2.isChecked());
        editor.commit();
    }

    public void delete(){
        SharedPreferences sharedPreferences =getSharedPreferences("user",MainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove("username");
        editor.remove("userpwd");
        editor.remove("chk");
        editor.commit();
    }
}


