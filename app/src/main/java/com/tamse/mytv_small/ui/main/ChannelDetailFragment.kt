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

@AndroidEntryPoint
class ChannelDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val viewModel: ChannelViewModel by activityViewModels()
    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
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


    }

    override fun onResume() {
        super.onResume()

        playerView = requireView().findViewById(R.id.playVideo)
        var newUrl = ""
        viewModel.play.observe(this.viewLifecycleOwner) {
            newUrl = DecryptUrlUtils.buildUrl(requireContext(), it.url, it.e)

        }
        val uri = newUrl.toUri().buildUpon().scheme("https").build()
        val mediaItem = MediaItem
            .fromUri(uri)

        simpleExoPlayer = SimpleExoPlayer.Builder(requireContext()).build()

        playerView.player = simpleExoPlayer


        simpleExoPlayer.addMediaItem(mediaItem)

        simpleExoPlayer.prepare()

        simpleExoPlayer.playWhenReady = true



    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.playWhenReady = false
        simpleExoPlayer.stop()
        simpleExoPlayer.seekTo(0)

    }


}