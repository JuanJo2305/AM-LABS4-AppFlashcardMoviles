package com.example.appflashcardmoviles

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.content.Intent
import kotlin.math.round

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val tiempoTotal = intent.getLongExtra("tiempoTotal", 0L)
        val minutos = (tiempoTotal / 1000) / 60
        val segundos = (tiempoTotal / 1000) % 60
        val tiempoTexto = String.format("%02d:%02d", minutos, segundos)

        val tvTiempoTotal = findViewById<TextView>(R.id.tvTiempoTotal)
        tvTiempoTotal.text = "Tiempo total: $tiempoTexto"


        val puntaje = intent.getIntExtra("puntaje", 0)
        val puntajeDecimal = round((puntaje / 20.0) * 10 * 100) / 100  // redondeo a 2 decimales
        val respuestasTexto = "$puntaje / 20"

        val tvPuntajeDecimal = findViewById<TextView>(R.id.tvPuntajeDecimal)
        val tvRespuestas = findViewById<TextView>(R.id.tvRespuestas)
        val tvMensajePodio = findViewById<TextView>(R.id.tvMensajePodio)

        tvPuntajeDecimal.text = "Puntaje: $puntajeDecimal"
        tvRespuestas.text = "Respuestas acertadas: $respuestasTexto"

        val mensaje = when {
            puntaje >= 18 -> "🥇 ¡Eres 1er puesto!"
            puntaje >= 15 -> "🥈 ¡Muy bien! Eres 2do puesto"
            puntaje >= 12 -> "🥉 Buen intento, 3er puesto"
            else -> "Sigue practicando para mejorar"
        }

        tvMensajePodio.text = mensaje

        val btnReiniciar = findViewById<Button>(R.id.btnReiniciar)


        btnReiniciar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
