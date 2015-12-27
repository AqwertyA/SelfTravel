package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.beans.UserInfo;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 15-11-30.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.back)
    private ImageView back;
    @ViewInject(R.id.commit)
    private TextView register;
    @ViewInject(R.id.password)
    private EditText password;
    @ViewInject(R.id.username)
    private EditText username;
    @ViewInject(R.id.error_hint)
    private TextView errorHint;
    @ViewInject(R.id.login)
    private Button login;
    @ViewInject(R.id.forget_password)
    private TextView forgetPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);
        x.view().inject(this);
        setListener();
    }

    private void setListener() {
        back.setOnClickListener(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.commit:
                jump(RegisterActivity.class);
                break;
            case R.id.login:
                logIn();
                break;
            default:
                break;
        }
    }

    private void logIn() {
        String usrname = username.getText().toString();
        String pwd = password.getText().toString();
        if (usrname == null || pwd == null || usrname.equals("") || pwd.equals("")) {
            errorHint.setVisibility(View.VISIBLE);
            return;
        } else {
            errorHint.setVisibility(View.GONE);
        }
        List<UserInfo> infos;
        DbManager manager = x.getDb(SelfTravelApp.daoConfig);
        try {
            infos = manager.selector(UserInfo.class).findAll();
            if (infos != null) {
                for (UserInfo info : infos) {
                    if (usrname.equals(info.getAccount()) && pwd.equals(info.password)) {
                        SelfTravelApp.LogIn(info);
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }
            if (!SelfTravelApp.isLoggedIn()) {
                Toast.makeText(this, "登录失败,请检查登录信息", Toast.LENGTH_SHORT).show();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    private void jump(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
