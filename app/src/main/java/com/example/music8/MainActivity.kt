package com.example.music8

import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.example.music8.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer:MediaPlayer
    private lateinit var handler: Handler
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // With Internet
//        mediaPlayer = MediaPlayer()
//        mediaPlayer.setDataSource("https://muzfm.belver.cloud/uploads/dl07/elyorbek-melibayev-eng-gullagan-yoshlik-chogimda.mp3")
//        mediaPlayer.setOnPreparedListener {
//            binding.seekBar.max = mediaPlayer.duration
//            mediaPlayer.start()
//        }
//        mediaPlayer.prepareAsync()


        //With Raw File 
        mediaPlayer = MediaPlayer.create(this, R.raw.oyijon)
        binding.seekBar.max = mediaPlayer.duration


        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2){
                    mediaPlayer.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })



        binding.btnPlay.setOnClickListener {
            mediaPlayer.start()
            binding.btnPlay.visibility = View.GONE
            binding.btnPause.visibility = View.VISIBLE
        }

        binding.btnPause.setOnClickListener {
            mediaPlayer.pause()
            binding.btnPlay.visibility = View.VISIBLE
            binding.btnPause.visibility = View.GONE
        }

        binding.btnSpeed.setOnClickListener {
            if (mediaPlayer.playbackParams.speed == 1.0f){
                mediaPlayer.playbackParams = mediaPlayer.playbackParams.setSpeed(2.0f)
            }else{
                mediaPlayer.playbackParams = mediaPlayer.playbackParams.setSpeed(1.0f)
            }
        }

        changingSeekBar()
    }

    fun changingSeekBar(){
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 1)
    }

    var runnable = object : Runnable{
        override fun run() {


            binding.seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(this, 1)
        }

    }
}