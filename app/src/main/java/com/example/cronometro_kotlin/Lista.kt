package com.example.cronometro_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Lista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
    }
    @SuppressLint("NonConstantResourceId")
    fun reproducirMusica(view: View) {
        val intent = Intent(this@Lista, reproductor::class.java)
        when (view.id) {
            R.id.anison1 -> intent.putExtra("CANCION", "drstoneop")
            R.id.anison2 -> intent.putExtra("CANCION", "fireforceop")
            R.id.anison3 -> intent.putExtra("CANCION", "inuyashikiop")
            R.id.anison4 -> intent.putExtra("CANCION", "spicexwolfop")
            R.id.anison5 -> intent.putExtra("CANCION", "tokyoghoulop")
            R.id.mencionhonorifica -> intent.putExtra("CANCION", "metalgearrisingrevengeance")
        }
        startActivity(intent)
    }
}