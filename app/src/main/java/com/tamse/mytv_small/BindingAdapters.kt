package com.tamse.mytv_small

import android.net.Uri
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDeepLinkRequest.Builder.Companion.fromUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem.fromUri
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.mp4.Track
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.ui.TrackSelectionView.TrackSelectionListener
import com.tamse.mytv_small.data.model.Channel
import com.tamse.mytv_small.data.model.PlayUrl
import com.tamse.mytv_small.ui.main.ChannelAdapter
import com.tamse.mytv_small.utils.Status
import vn.mytv.b2c.androidtv.common.utils.DecryptUrlUtils

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

//@BindingAdapter("playerVideo")
//fun bindPlayVideo(playerView: PlayerView, playUrl: PlayUrl?) {
//    playUrl?.let {
//        val context = playerView.context
//        val newUrl = DecryptUrlUtils.buildUrl(context, playUrl.url, playUrl.e)
//        val uri = newUrl.toUri().buildUpon().scheme("https").build()
//        val mediaItem = com.google.android.exoplayer2.MediaItem
//            .fromUri(uri)
//
//        val simpleExoPlayer = SimpleExoPlayer.Builder(context).build()
//
//        playerView.player = simpleExoPlayer
//
//        simpleExoPlayer.addMediaItem(mediaItem)
//
//        simpleExoPlayer.prepare()
//
//        simpleExoPlayer.playWhenReady = true
//
//    }
//}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: MutableList<Channel>?) {
    val adapter = recyclerView.adapter as ChannelAdapter

    adapter.submitList(data)
}

@BindingAdapter("channelStatus")
fun bindStatus(statusImageView: ImageView,
            status: Status?){
    when (status) {
        Status.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        Status.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        Status.SUCCESS -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}


