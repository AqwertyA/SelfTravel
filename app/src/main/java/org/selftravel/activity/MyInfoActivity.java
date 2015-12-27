package org.selftravel.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.beans.UserInfo;
import org.selftravel.callback.TitleBarClickListener;
import org.selftravel.utils.BitmapUtil;
import org.selftravel.view.CircleImageView;
import org.selftravel.view.TitleBar;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Administrator on 15-12-1.
 */

@ContentView(R.layout.activity_myinfo)
public class MyInfoActivity extends BaseActivity {

    private static final String TAG = MyInfoActivity.class.getSimpleName();
    public static final int REQUEST_CODE = 1;
    public static final int REQUEST_CODE2 = 2;
    /* 头像名称 */
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";

    @ViewInject(R.id.head_photo)
    private CircleImageView headPhoto;
    @ViewInject(R.id.nickname_tv)
    private TextView nickNameTV;
    @ViewInject(R.id.nickname_et)
    private EditText nickNameET;
    @ViewInject(R.id.gender_tv)
    private TextView gender;
    @ViewInject(R.id.birthday_tv)
    private TextView birthday;
    @ViewInject(R.id.address_tv)
    private TextView address;
    @ViewInject(R.id.titlebar)
    private TitleBar titleBar;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        initData();
    }

    private void initData() {
        UserInfo userInfo = SelfTravelApp.getUserInfo();
        String headPhotoPath = userInfo.headimg;
        Toast.makeText(this, headPhotoPath, Toast.LENGTH_SHORT).show();
        if (headPhotoPath != null && !headPhotoPath.equals("")) {
            Bitmap bitmap = BitmapUtil.getBitmapFromFile(headPhotoPath);
            headPhoto.setImageBitmap(bitmap);
        }
        nickNameTV.setText(userInfo.username);
        nickNameET.setText(userInfo.username);
        gender.setText(userInfo.gender);
        birthday.setText(userInfo.birthday);
        address.setText(userInfo.address);

    }

    private void initView() {
        titleBar.setTitle("我的资料");
        titleBar.setRightBkg(R.drawable.album_edit);
        titleBar.setsetRightTvClickListener(new MyOnClickListener());
        titleBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }


    @Event(value = {R.id.head_photo, R.id.gender_tv, R.id.birthday_tv, R.id.address_tv})
    private void onClick(View v) {

        if (isEdit) {
            switch (v.getId()) {
                case R.id.head_photo:
                    new AlertDialog.Builder(this)
                            .setItems(new String[]{"拍照", "选择照片"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    switch (which) {
                                        case 0:
                                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            MyInfoActivity.this.startActivityForResult(intent, REQUEST_CODE);
                                            break;
                                        case 1:
                                            Intent intentFromGallery = new Intent();
                                            intentFromGallery.setType("image/*"); // 设置文件类型
                                            intentFromGallery
                                                    .setAction(Intent.ACTION_GET_CONTENT);
                                            intentFromGallery.putExtra("return-data", true);
                                            startActivityForResult(intentFromGallery,
                                                    REQUEST_CODE2);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            })
                            .show();
                    break;
                case R.id.gender_tv:

                   /* new AlertDialog.Builder(this)
                            .setView(R.layout.view_gender)
                            .show();*/

                    new AlertDialog.Builder(this)
                            .setTitle("请选择性别")
                            .setSingleChoiceItems(new String[]{"男", "女", "保密"}, -1,
                                    new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            switch (which) {
                                                case 0:
                                                    gender.setText("男");
                                                    break;
                                                case 1:
                                                    gender.setText("女");
                                                    break;
                                                case 2:
                                                    gender.setText("保密");
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    }
                            )
                            .show();
                    break;
                case R.id.birthday_tv:
                    Calendar calendar = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            MyInfoActivity.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view,
                                              int year, int monthOfYear,
                                              int dayOfMonth) {
                            birthday.setText(year
                                    + "年" + (monthOfYear + 1) + "月"
                                    + dayOfMonth + "日");
                        }
                    }, calendar.get(Calendar.YEAR), calendar
                            .get(Calendar.MONTH), calendar
                            .get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.setCancelable(true);
                    datePickerDialog.show();
                    break;
                case R.id.address_tv:

                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            headPhoto.setImageBitmap(bitmap);
            String path = BitmapUtil.saveBitmap(bitmap, SelfTravelApp.getUserInfo().getAccount() + IMAGE_FILE_NAME);
            headPhoto.setTag(path);
        } else if (requestCode == REQUEST_CODE2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                headPhoto.setImageBitmap(bitmap);
                String path = BitmapUtil.saveBitmap(bitmap, SelfTravelApp.getUserInfo().getAccount() + IMAGE_FILE_NAME);
                headPhoto.setTag(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyOnClickListener implements TitleBarClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(MyInfoActivity.this, "编辑被点了", Toast.LENGTH_SHORT).show();

            if (isEdit) {

                titleBar.setRightBkg(R.drawable.album_edit);
                titleBar.setRightText("");
                nickNameET.setVisibility(View.INVISIBLE);
                nickNameTV.setText(nickNameET.getText());
                nickNameTV.setVisibility(View.VISIBLE);
                isEdit = !isEdit;

                UserInfo userInfo = SelfTravelApp.getUserInfo();
                userInfo.headimg = (String) headPhoto.getTag();
                userInfo.birthday = birthday.getText().toString();
                userInfo.gender = gender.getText().toString();
                userInfo.username = nickNameTV.getText().toString();
                userInfo.address = address.getText().toString();


                try {
                    x.getDb(SelfTravelApp.daoConfig).update(userInfo, WhereBuilder.b("account", "=", userInfo.getAccount()), "headimg", "birthday", "gender", "username", "address");
                    Log.e(TAG, "save to db success");
                } catch (DbException e) {
                    e.printStackTrace();
                }

            } else {

                titleBar.removeRightBkg();
                titleBar.setRightText("确定");
                nickNameET.setVisibility(View.VISIBLE);
                nickNameTV.setVisibility(View.INVISIBLE);
                isEdit = !isEdit;

            }

        }
    }
}
