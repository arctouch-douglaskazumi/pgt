package com.dk.pgt.main

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dk.pgt.R
import com.dk.pgt.floater.PoGoToolsService

import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by douglaskazumi on 9/20/16.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainPresenter = MainPresenter(this)

        button.setOnClickListener { mainPresenter.launchService(); }

        if (!isServiceAlreadyRunning) {
            mainPresenter.launchService()
        }
    }

    private val isServiceAlreadyRunning: Boolean
        get() {
            val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (info in manager.getRunningServices(Integer.MAX_VALUE)) {
                if (PoGoToolsService::class.java.name == info.service.className) {
                    return true
                }
            }
            return false
        }
}