package com.example.mrtest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //dialogFragment

    MyDialogFragment myDialogFragment;



    private final String acc="acc";
    private final String pwd2="pwd";

    private SharedPreferences.Editor editor;
    private SharedPreferences ge;


    public Intent intent;
    private ProgressDialog pr;


    private TextInputEditText te;
    private TextInputLayout in;
    private TextInputLayout in2;
    private TextInputEditText te2;
    private String account="1112";
    private String pwd="1015";
    private ImageView img;

    private TextView textView1;
    private TextView textView2;

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar =getSupportActionBar();
        actionBar.hide();
        //实现存储
       editor=getSharedPreferences("msg",MODE_PRIVATE).edit();
       ge=getSharedPreferences("msg",MODE_PRIVATE);



        Button button = findViewById(R.id.TEST);


        button.setOnClickListener(this);
        in = findViewById(R.id.TTT);
        in2 = findViewById(R.id.UUU);
        te = findViewById(R.id.YYY);
        te2 = findViewById(R.id.III);
        img = findViewById(R.id.Img);

        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);



        checkBox = findViewById(R.id.check_box);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getFragmentManager(), "show");
            }
        });



        textView2.setOnClickListener(this);

        if(ge.getInt("check",0)==1){
            checkBox.setChecked(true);

            String temp1=ge.getString(acc,"");
            account=temp1;
            if(temp1.equals("1112")){
                img.setImageResource(R.drawable.test4);
            }
            te.setText(temp1);
            String temp2=ge.getString(pwd2,"");
            te2.setText(temp2);
            pwd=temp2;
        }else{
            editor.putString(acc,"");
            editor.putString(pwd2,"");
            editor.apply();
        }

        check(te, te2);
      isChecked();


    }


    public void check(TextInputEditText te, TextInputEditText te2) {

        te.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (te.getText().toString().length() > 10) {
                    in.setError("账号输入长度有误，请重新输入");
                } else {
                    in.setError("");
                }

                if (te.getText().toString().equals("1112")) {

                    img.setImageResource(R.drawable.test4);
                } else {
                    img.setImageResource(R.drawable.test3);
                }

            }
        });

        te2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (te2.getText().toString().length() > 50) {
                    in2.setError("密码输入长度不能超过50位");
                } else {
                    in2.setError("");
                }
            }
        });
    }


    public void st(Intent intent) {
        pr.cancel();
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TEST: {
                signIn();
                break;

            }
        }
    }


    public void signIn() {


        if (te.getText().toString().equals(account)&&te2.getText().toString().equals(pwd) ){

            editor.putString(acc,account);
            editor.putString(pwd2,pwd);
            editor.apply();

            intent = new Intent(MainActivity.this, Activity2.class);
            NewThread n = new NewThread(intent, this);
            pr = new ProgressDialog(this);
            pr.setTitle("正在登陆");
            pr.setMessage("请耐心等待......");

            pr.setCancelable(false);
            pr.show();
            new Thread(n).start();


        } else if (te.getText().toString().length() == 0) {

            in.setError("账号不能为空");
            in2.setError("密码不能为空");
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("输入的账号或密码错误");
            dialog.setCancelable(true);
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dialog.show();
        }
    }


    public void isChecked(){
        //记住密码功能以及，记忆复选框勾选状态
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    editor.putInt("check",1);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "你记不住我帮你记", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putInt("check",0);
                    editor.apply();
                }
            }
        });
    }

    public SharedPreferences.Editor getEditor(){

        editor.putInt("check",1);
        checkBox.setChecked(true);

        return this.editor;
    }


    public void re(){
        String temp1=ge.getString(acc,"");
        te.setText(temp1);
        account=temp1;
        String temp2=ge.getString(pwd2,"");
        te2.setText(temp2);
        pwd=temp2;
    }
}