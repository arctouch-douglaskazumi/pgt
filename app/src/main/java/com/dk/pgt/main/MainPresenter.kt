package com.dk.pgt.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import com.dk.pgt.floater.PoGoToolsService


/**
 * Created by douglaskazumi on 9/19/16.
 */
class MainPresenter(val activity: MainActivity) {

    var OVERLAY_PERMISSION_REQ_CODE = 1234

    fun launchService() {
        if (canDrawOverlays(activity)) {
            activity.startService(Intent(activity, PoGoToolsService::class.java))
            activity.finish()
        } else {
            requestPermission(OVERLAY_PERMISSION_REQ_CODE)
        }
    }

    fun canDrawOverlays(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        return Settings.canDrawOverlays(context)
    }

    private fun requestPermission(requestCode: Int) {
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        intent.setData(Uri.parse(String.format("package:%s", activity.getPackageName())))
        activity.startActivityForResult(intent, requestCode)
    }
}