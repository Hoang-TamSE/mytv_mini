package com.tamse.mytv_small.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tamse.mytv_small.R
import com.tamse.mytv_small.ui.main.LiveFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExoPlayerActivity : AppCompatActivity() {

    private var liveFragment: LiveFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)

        val buttonShowFragment = findViewById<Button>(R.id.buttonShowFragment)
        buttonShowFragment.setOnClickListener {
            showLiveFragment()
        }
    }

    private fun showLiveFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, LiveFragment(), "")
            .commit()

    }
}