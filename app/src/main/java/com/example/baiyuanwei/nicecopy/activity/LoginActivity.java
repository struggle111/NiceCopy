package com.example.baiyuanwei.nicecopy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.example.baiyuanwei.nicecopy.R;
import com.example.baiyuanwei.nicecopy.base.BaseActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by baiyuanwei on 16/8/15.
 */
@EActivity
public class LoginActivity extends BaseActivity {

    @ViewById(R.id.account_edit)
    EditText accountEdit;

    @ViewById(R.id.password_edit)
    EditText passwordEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    @Click(R.id.login_btn)
    protected void loginListener() {
        String account = accountEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();

//        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
//            Toast.makeText(LoginActivity.this, "请输入登录信息", Toast.LENGTH_SHORT).show();
//        } else {
//
//        }

        Intent intent = MainActivity_.intent(this).get();
        startActivity(intent);
    }
}
