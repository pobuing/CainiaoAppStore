package com.xinw.cainiaoappstore.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.common.util.DeviceUtils;

public class LoginActivity extends AppCompatActivity {

    private static final int READ_PHONE_STATE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void checkPermission(View view) {
        // TODO: 未被授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_CODE);
        } else {
            // TODO: 已授权
            String imei = DeviceUtils.getIMEI(this);
            Toast.makeText(this, "imei" + imei, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == READ_PHONE_STATE_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String imei = DeviceUtils.getIMEI(this);
                Toast.makeText(this, "imei" + imei, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户拒绝授权", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
