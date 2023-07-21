package com.tamse.mytv_small.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.tamse.mytv_small.R
import com.tamse.mytv_small.databinding.FragmentChannelDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import vn.mytv.b2c.androidtv.common.utils.DecryptUrlUtils

const val URL_KEY = "URL_KEY"
@AndroidEntryPoint
class ChannelDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: ChannelViewModel by activityViewModels()

    private var simpleExoPlayer: ExoPlayer? = null
    private var playerView: PlayerView? = null
    private var mediaItem: MediaItem? = null
    private var newUrl: String = ""
    private var url: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentChannelDetailBinding.inflate(inflater)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.play.observe(this.viewLifecycleOwner) { value ->
            playerView = requireView().findViewById(R.id.playVideo)
            val context = playerView!!.context
            if (value != null) {
                newUrl = DecryptUrlUtils.buildUrl(context, value.url, value.e)
            }else if (savedInstanceState != null && value == null) {
                newUrl = savedInstanceState.getString(URL_KEY, "")
            }
            val uri = newUrl.toUri().buildUpon().scheme("https").build()
            mediaItem = MediaItem
                .fromUri(uri)
            simpleExoPlayer = SimpleExoPlayer.Builder(context).build()

            playerView!!.player = simpleExoPlayer


            simpleExoPlayer!!.addMediaItem(mediaItem!!)

            simpleExoPlayer!!.prepare()

            simpleExoPlayer!!.playWhenReady = true
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(URL_KEY, url)
        super.onSaveInstanceState(outState)
    }


    fun releasePlayer() {
        if(simpleExoPlayer != null){
            simpleExoPlayer?.run {
                playWhenReady = false
                stop()
                release()
            }
            simpleExoPlayer = null
            playerView?.player = null
            mediaItem = null
            if (newUrl != ""){
                url = newUrl
            }
            newUrl = ""
            viewModel.setNullForPlay()
        }
    }

}