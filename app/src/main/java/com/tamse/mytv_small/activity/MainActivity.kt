package com.tamse.mytv_small.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.pedrovgs.DraggablePanel
import com.tamse.mytv_small.R
import com.tamse.mytv_small.ui.main.ChannelDetailFragment
import com.tamse.mytv_small.ui.main.ChannelFragment
import com.tamse.mytv_small.ui.main.LiveFragment
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ChannelFragment.ChannelClickListener {

    var isPipMode: Boolean ?= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if(!isPipMode!!) {
            enterPictureInPictureMode()
            isPipMode = true
        }else {
            super.onUserLeaveHint()
        }
    }

    override fun onPause() {
        super.onPause()
        isPipMode = false
    }

    override fun onChannelClicked() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutMain, LiveFragment(), "")
            .commit()
    }


}