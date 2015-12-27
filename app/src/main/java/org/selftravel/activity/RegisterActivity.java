package org.selftravel.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.beans.UserInfo;
import org.selftravel.callback.TitleBarClickListener;
import org.selftravel.utils.SharedPreferencesUtils;
import org.selftravel.view.TitleBar;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements TitleBarClickListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;
    @ViewInject(R.id.username)
    private EditText usernameEt;
    @ViewInject(R.id.password)
    private EditText passwordEt;
    @ViewInject(R.id.password_confirm)
    private EditText passwordConfirmEt;
    @ViewInject(R.id.error_hint)
    private TextView errorHintTv;
    @ViewInject(R.id.checkbox_treaty)
    private CheckBox treatyCb;
    @ViewInject(R.id.commit)
    private Button commitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        initData();
    }

    private void initView() {
        x.view().inject(this);
    }

    private void setListener() {
        titleBar.setsetRightTvClickListener(this);
        treatyCb.setOnCheckedChangeListener(this);
        commitBtn.setOnClickListener(this);
    }

    private void initData() {
        titleBar.setTitle("注册账号");
        titleBar.setRightText("登录");
        treatyCb.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.commit:
                commit();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_treaty:
                if (isChecked) {
                    commitBtn.setEnabled(true);
                } else {
                    commitBtn.setEnabled(false);
                }
                break;
        }
    }

    private void commit() {
        String usrname = usernameEt.getText().toString();
        String pwd = passwordEt.getText().toString();
        String pwdConfirm = passwordConfirmEt.getText().toString();
        if (/*usrname == null ||*/ usrname.equals("")) {
            errorHintTv.setVisibility(View.VISIBLE);
            errorHintTv.setText("用户名格式不正确。请输入邮箱或手机号！");
            /*return;*/
        } else if (/*pwd == null ||*/ pwd.length() < 6) {
            errorHintTv.setVisibility(View.VISIBLE);
            errorHintTv.setText("密码太短！至少要有6位！");
        } else if (!pwd.equals(pwdConfirm)) {
            errorHintTv.setVisibility(View.VISIBLE);
            errorHintTv.setText("两次输入的密码不一致！请检查！");
        } else {
            try {
                DbManager manager = x.getDb(SelfTravelApp.daoConfig);
                List<UserInfo> infoList = manager.selector(UserInfo.class).findAll();
                boolean existUser = false;
                if (infoList != null) {
                    for (UserInfo info : infoList) {
                        if (info.getAccount().equals(usrname)) {
                            errorHintTv.setText("用户名已存在！");
                            errorHintTv.setVisibility(View.VISIBLE);
                            existUser = true;
                            break;
                        }
                    }
                }
                if (!existUser) {
                    UserInfo info = new UserInfo(usrname, pwd);
                    manager.save(info);
                    SharedPreferencesUtils.putString(this, "username", usrname);
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }
}
