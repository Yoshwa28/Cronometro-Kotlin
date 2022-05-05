package com.example.cronometro_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import java.util.concurrent.TimeUnit

class reproductor : AppCompatActivity() {

    var portada: ImageView? = null
    var titulo: TextView? = null
    var tiempo: TextView? = null
    var duracion: TextView? = null
    var autor: TextView? = null
    var album: TextView? = null
    var anio: TextView? = null
    var transcurso: SeekBar? = null
    var btnRetroceder: ImageView? = null
    var btnPlay: ImageView? = null
    var btnPausa: ImageView? = null
    var btnAdelantar: ImageView? = null
    var mediaPlayer: MediaPlayer? = null
    var handler = Handler()
    var runnable: Runnable? = null
    var CANCION: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor)

        portada = findViewById(R.id.portada)
        titulo = findViewById(R.id.titulo)
        tiempo = findViewById(R.id.tiempo)
        duracion = findViewById(R.id.duracion)
        autor = findViewById(R.id.autor)
        album = findViewById(R.id.album)
        anio = findViewById(R.id.anio)
        transcurso = findViewById(R.id.transcurso)
        btnRetroceder = findViewById(R.id.btnRetroceder)
        btnPlay = findViewById(R.id.btnPlay)
        btnPausa = findViewById(R.id.btnPausa)
        btnAdelantar = findViewById(R.id.btnAdelantar)
        CANCION = intent.extras!!.getString("CANCION")
        when (CANCION) {
            "drstoneop" -> {
                titulo!!.setText("BURNOUT SYNDROMES『Good Morning World!』（TVアニメ「Ｄｒ．ＳＴＯＮＥ」オープニングテーマ）")
                portada!!.setBackground(resources.getDrawable(R.drawable.drstone))
                autor!!.setText("Autor: Burnout Syndromes")
                album!!.setText("Album: Burnout Syndromes")
                anio!!.setText("Año: 2020")
            }
            "fireforceop" -> {
                titulo!!.setText("Mrs. GREEN APPLE - インフェルノ（Inferno）")
                portada!!.setBackground(resources.getDrawable(R.drawable.enen))
                autor!!.setText("Autor: Mrs. GREEN APPLE")
                album!!.setText("Album: Attitude")
                anio!!.setText("Año: 2019")
            }
            "inuyashikiop" -> {
                titulo!!.setText("MAN WITH A MISSION - My Hero")
                portada!!.setBackground(resources.getDrawable(R.drawable.inuyashiki))
                autor!!.setText("Autor: Man with a Mission")
                album!!.setText("Album: Chasing the Horizon")
                anio!!.setText("Año: 2017")
            }
            "spicexwolfop" -> {
                titulo!!.setText("Tabi no Tochuu ~Natsumi Kiyoura~ •Spice and Wolf")
                portada!!.setBackground(resources.getDrawable(R.drawable.spicexwolf))
                autor!!.setText("Autor: Natsumi Kiyoura")
                album!!.setText("Album: Spice and Wolf")
                anio!!.setText("Año: 2010")
            }
            "tokyoghoulop" -> {
                titulo!!.setText("TK from 凛として時雨 『unravel』")
                portada!!.setBackground(resources.getDrawable(R.drawable.tokioghoul))
                autor!!.setText("Autor: Tōru Kitajima")
                album!!.setText("Album: Fantastic Magic")
                anio!!.setText("Año: 2014")
            }
            "metalgearrisingrevengeance" -> {
                titulo!!.setText("Metal Gear Rising: Revengeance OST It Has To Be This Way")
                portada!!.setBackground(resources.getDrawable(R.drawable.mgr))
                autor!!.setText("Autor: Jamie Christopherson")
                album!!.setText("Album: Metal Gear Rising: Revengeance (Vocal Tracks)")
                anio!!.setText("Año: 2013")
            }
        }
        btnPlay!!.setOnClickListener(View.OnClickListener {
            btnPlay!!.setVisibility(View.GONE)
            btnPausa!!.setVisibility(View.VISIBLE)
            reproducir()
            mediaPlayer!!.start()
            transcurso!!.setMax(mediaPlayer!!.duration)
            handler.postDelayed(runnable!!, 0)
        })
        btnPausa!!.setOnClickListener(View.OnClickListener {
            btnPausa!!.setVisibility(View.GONE)
            btnPlay!!.setVisibility(View.VISIBLE)
            mediaPlayer!!.pause()
            handler.removeCallbacks(runnable!!)
        })
        btnAdelantar!!.setOnClickListener(View.OnClickListener {
            var actual = mediaPlayer!!.currentPosition
            val durar = mediaPlayer!!.duration
            if (mediaPlayer!!.isPlaying && durar != actual) {
                actual += 5000
                tiempo!!.setText(convertFormat(actual))
                mediaPlayer!!.seekTo(actual)
            }
        })
        btnRetroceder!!.setOnClickListener(View.OnClickListener {
            var actual = mediaPlayer!!.currentPosition
            if (mediaPlayer!!.isPlaying && actual > 5000) {
                actual -= 5000
                tiempo!!.setText(convertFormat(actual))
                mediaPlayer!!.seekTo(actual)
            }
        })
    }


    fun reproducir() {
        when (CANCION) {
            "drstoneop" -> mediaPlayer = MediaPlayer.create(this, R.raw.drstoneop)
            "fireforceop" -> mediaPlayer = MediaPlayer.create(this, R.raw.enenop)
            "inuyashikiop" -> mediaPlayer = MediaPlayer.create(this, R.raw.inuyashikiop)
            "spicexwolfop" -> mediaPlayer = MediaPlayer.create(this, R.raw.spicexwolfop)
            "tokyoghoulop" -> mediaPlayer = MediaPlayer.create(this, R.raw.tokyoghoulop)
            "metalgearrisingrevengeance" -> mediaPlayer = MediaPlayer.create(this, R.raw.metalgearrisingrevengeance)
        }
        runnable = object : Runnable {
            override fun run() {
                transcurso!!.progress = mediaPlayer!!.currentPosition
                handler.postDelayed(this, 500)
            }
        }
        val durar = mediaPlayer!!.duration
        val Sdurar = convertFormat(durar)
        duracion!!.text = Sdurar
        transcurso!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer!!.seekTo(i)
                }
                tiempo!!.text = convertFormat(mediaPlayer!!.currentPosition)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        mediaPlayer!!.setOnCompletionListener { mediaPlayer ->
            btnPausa!!.visibility = View.GONE
            btnPlay!!.visibility = View.VISIBLE
            mediaPlayer.seekTo(0)
        }
    }

    @SuppressLint("DefaultLocale")
    private fun convertFormat(durar: Int): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(durar.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(durar.toLong()) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(durar.toLong())
            )
        )
    }
}