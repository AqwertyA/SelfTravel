package org.selftravel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.beans.UserInfo;
import org.selftravel.view.TitleBar;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_change_pwd)
public class ChangePwdActivity extends BaseActivity {
    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;
    @ViewInject(R.id.old_password)
    private EditText oldPwdEt;
    @ViewInject(R.id.password)
    private EditText newPwdEt;
    @ViewInject(R.id.password_confirm)
    private EditText confirmPwdEt;
    @ViewInject(R.id.error_hint)
    private EditText errorHintTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        titleBar.setTitle("修改密码");
    }

    @Event(value = R.id.commit)
    private void onClick(View v) {
        String oldPwd = oldPwdEt.getText().toString();
        String newPwd = newPwdEt.getText().toString();
        String confirmPwd = confirmPwdEt.getText().toString();

        if (!oldPwd.equals(SelfTravelApp.getUserInfo().password)) {
            Toast.makeText(this, "旧密码输入错误！", Toast.LENGTH_SHORT).show();
            errorHintTv.setText("旧密码输入错误！");
            errorHintTv.setVisibility(View.VISIBLE);
        } else if (newPwd.length() < 6) {
            Toast.makeText(this, "新密码太短！", Toast.LENGTH_SHORT).show();
            errorHintTv.setText("旧密码输入错误！");
            errorHintTv.setVisibility(View.VISIBLE);
        } else if (!newPwd.equals(confirmPwd)) {
            Toast.makeText(this, "新密码太短！", Toast.LENGTH_SHORT).show();
            errorHintTv.setText("旧密码输入错误！");
            errorHintTv.setVisibility(View.VISIBLE);
        } else {
            UserInfo userInfo = SelfTravelApp.getUserInfo();
            userInfo.password = newPwd;
            try {
                x.getDb(SelfTravelApp.daoConfig).update(userInfo, "password");
                Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                errorHintTv.setVisibility(View.GONE);
                finish();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }


    }
}
