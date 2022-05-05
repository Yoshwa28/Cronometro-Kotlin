package com.example.cronometro_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import java.util.*

class MainActivity : AppCompatActivity() {


    var la_centerAnimation: LottieAnimationView? = null

    private var segundo = 0
    private var correr = false              //funcionando
    private var corriendo = false           //estaba ejecutando
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        la_centerAnimation = findViewById<View>(R.id.la_centerAnimation) as LottieAnimationView

        if (savedInstanceState != null) {
            segundo = savedInstanceState.getInt("segundo")
            correr = savedInstanceState.getBoolean("correr")
            corriendo = savedInstanceState.getBoolean("wasRunning")
        }
        Tiempo()
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("segundo", segundo)
        savedInstanceState.putBoolean("correr", correr)
        savedInstanceState.putBoolean("corriendo", corriendo)
    }

    override fun onPause() {
        super.onPause()
        corriendo = correr
        correr = false
    }

    override fun onResume() {
        super.onResume()
        if (corriendo) {
            correr = true
        }
    }

    fun onClickStart(view: View?) {
        correr = true
        la_centerAnimation!!.playAnimation()
    }

    fun onClickStop(view: View?) {
        correr = false
        la_centerAnimation!!.pauseAnimation()
    }

    fun onClickReset(view: View?) {
        correr = false
        segundo = 0
        la_centerAnimation!!.playAnimation()
        la_centerAnimation!!.pauseAnimation()
    }

    private fun Tiempo() {
        val verTiempo = findViewById<View>(R.id.verTiempo) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val horas = segundo / 3600
                val minutos = segundo % 3600 / 60
                val secs = segundo % 60
                val time = String.format(Locale.getDefault(), "%02d:%02d:%02d", horas, minutos, secs)
                verTiempo.text = time
                if (correr) {
                    segundo++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}