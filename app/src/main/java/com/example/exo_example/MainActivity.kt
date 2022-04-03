package com.example.exo_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {
    private var mPlayer : SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView
    private val videoURL =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.playerView)
    }

    private fun initPlayer() {
        mPlayer = SimpleExoPlayer.Builder(this).build()

        playerView.player = mPlayer

        mPlayer!!.playWhenReady = true
        mPlayer?.let {
            it.playWhenReady = true
            it.setMediaSource(buildMediaSource())
            it.prepare()
        }
    }

    private fun buildMediaSource(): MediaSource {
        val dataSourceFactory = DefaultHttpDataSource.Factory()
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(videoURL))
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onStop() {
        super.onStop()
        mPlayer?.release()
    }
}