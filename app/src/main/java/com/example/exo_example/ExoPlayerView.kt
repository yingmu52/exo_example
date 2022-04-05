package com.example.exo_example

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Toast
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class ExoPlayerView(
    context: Context,
    attrs: AttributeSet? = null,
): PlayerView(context, attrs) {

    private val exoPlayer = SimpleExoPlayer.Builder(context).build()

    init {
        exoPlayer.playWhenReady = true
        exoPlayer.setMediaSource(buildMediaSource())
        exoPlayer.prepare()

        this.player = exoPlayer
        this.useController = false
    }

    private fun buildMediaSource(): MediaSource {
        val dataSourceFactory = DefaultHttpDataSource.Factory()
        val videoURL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        return ProgressiveMediaSource
            .Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(videoURL))
    }

    fun togglePlay() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            Toast.makeText(context, "paused", Toast.LENGTH_SHORT).show()
        } else {
            exoPlayer.play()
            Toast.makeText(context, "playing", Toast.LENGTH_SHORT).show()
        }
    }
}