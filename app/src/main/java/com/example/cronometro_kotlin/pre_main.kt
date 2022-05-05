package com.example.cronometro_kotlin

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class pre_main : AppCompatActivity() {
    var btn_prebutton: Button? = null
    var pre_crono: Button? = null
    var m: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_main)
        m = MediaPlayer.create(this, R.raw.music)
        m!!.isLooping = true
        m!!.start()
        btn_prebutton = findViewById(R.id.pre_button)
        btn_prebutton!!.setOnClickListener {
            m!!.stop()
            val mp: MediaPlayer = MediaPlayer.create(this@pre_main, R.raw.prebutton)
            mp.start()
            val intent = Intent(this@pre_main, Lista::class.java)
            startActivity(intent)
        }

        pre_crono = findViewById(R.id.pre_crono)
        pre_crono!!.setOnClickListener {
            m!!.stop()
            val mp: MediaPlayer = MediaPlayer.create(this@pre_main, R.raw.prebutton)
            mp.start()
            val intent = Intent(this@pre_main, MainActivity::class.java)
            startActivity(intent)
        }
    }
}