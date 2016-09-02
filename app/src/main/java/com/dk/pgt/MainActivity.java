package com.dk.pgt;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dk.pgt.databinding.ActivityMainBinding;
import com.dk.pgt.floater.PoGoToolsService;

public class MainActivity extends AppCompatActivity implements MainView {

    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    public static boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        return Settings.canDrawOverlays(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainView(this);
    }

    public void launchService(View view) {
        if (canDrawOverlays(MainActivity.this))
            startService(new Intent(MainActivity.this, PoGoToolsService.class));
        else {
            requestPermission(OVERLAY_PERMISSION_REQ_CODE);
        }
    }

    private void requestPermission(int requestCode) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, requestCode);
    }

}
