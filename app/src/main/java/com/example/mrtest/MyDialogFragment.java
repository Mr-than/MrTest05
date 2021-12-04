package com.example.mrtest;

import android.app.DialogFragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MyDialogFragment extends DialogFragment implements View.OnClickListener {

    //SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private View view;
    private Button button;

    private String account;
    private String pwd;
    private String pwd2;


    //提示信息
    public TextInputLayout textInputLayout01;
    public TextInputLayout textInputLayout02;
    public TextInputLayout textInputLayout03;

    public TextInputEditText textInputEditText01;
    public TextInputEditText textInputEditText02;
    public TextInputEditText textInputEditText03;

    public MainActivity mainActivity;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (view == null) {
            view = inflater.inflate(R.layout.dialogfragment, container);
            this.button = view.findViewById(R.id.butt1);
        }
        //初始化控件
        textInputLayout01 = view.findViewById(R.id.dialog_text1);
        textInputLayout02 = view.findViewById(R.id.dialog_text2);
        textInputLayout03 = view.findViewById(R.id.dialog_text3);
        //----------------------------------------------------------
        textInputEditText01 = view.findViewById(R.id.dialog_edit1);
        textInputEditText02 = view.findViewById(R.id.dialog_edit2);
        textInputEditText03 = view.findViewById(R.id.dialog_edit3);

        button.setOnClickListener(this);


        check();
        return view;
    }

    //editText的输入事件

    public void check() {


        textInputEditText01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputEditText01.getText().toString().length() > 10) {
                    textInputLayout01.setError("账号的输入长度有误，请重新输入！");

                } else {
                    textInputLayout01.setError("");
                    account = textInputEditText01.getText().toString();
                }
            }
        });

        textInputEditText02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pwd = textInputEditText02.getText().toString();

            }
        });

        textInputEditText03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputEditText03.getText().toString().equals(pwd)) {
                    pwd2 = pwd;
                }
                textInputLayout03.setError("");
                textInputLayout02.setError("");

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butt1: {
                account = textInputEditText01.getText().toString();
                pwd = textInputEditText02.getText().toString();
                pwd2 = textInputEditText03.getText().toString();

                if (pwd.length() > 0 && pwd.equals(pwd2) && account.length() > 1) {
                    mainActivity = (MainActivity) getActivity();
                    editor = mainActivity.getEditor();

                    editor.putString("acc", account);
                    editor.putString("pwd", pwd);
                    editor.apply();
                    mainActivity.re();

                } else if (!pwd.equals(pwd2)) {
                    textInputLayout03.setError("两次输入的密码不相同");
                    textInputLayout02.setError("两次输入的密码不相同");
                }
                if (textInputEditText01.getText().toString().length() < 1) {
                    textInputLayout01.setError("请输入账号");
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(200);

                            dismiss();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }
}

