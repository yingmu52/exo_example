package com.example.exo_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var playerView: ExoPlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.player_view)
        playerView?.setOnClickListener {
            (it as? ExoPlayerView)?.let {
               it.togglePlay()
            }
        }
    }
}
