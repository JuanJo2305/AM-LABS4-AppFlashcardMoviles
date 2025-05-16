package com.example.appflashcardmoviles
// Aguilar Villafana Juan José
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.*
import android.widget.*

class MainActivity : AppCompatActivity() {


    private val preguntas = listOf(
        Pregunta(
            "¿Qué componente de la arquitectura Android es responsable de la compilación AOT/JIT del bytecode DEX?",
            listOf("Kernel de Linux", "Android Runtime (ART)", "Hardware Abstraction Layer (HAL)", "Framework de Aplicaciones"),
            1,
            "ART (Android Runtime) es el entorno de ejecución que compila el bytecode DEX a código nativo usando compilación Ahead-of-Time (AOT) y Just-in-Time (JIT), optimizando el rendimiento de las aplicaciones."
        ),
        Pregunta(
            "¿Qué mecanismo de IPC (Inter-Process Communication) es crucial en Android para la comunicación entre procesos?",
            listOf("Intent", "Binder", "Content Provider", "Broadcast Receiver"),
            1,
            "Binder es el mecanismo IPC central de Android, permitiendo comunicación eficiente entre procesos mediante un sistema de llamadas a métodos remotos (RPC)."
        ),
        Pregunta(
            "¿Qué librería de AndroidX se utiliza específicamente para gestionar datos persistentes con SQLite?",
            listOf("ViewModel", "WorkManager", "Room Persistence Library", "Navigation Component"),
            2,
            "Room es una capa de abstracción sobre SQLite que simplifica las operaciones de base de datos, proporcionando anotaciones para definir entidades, DAOs y consultas."
        ),
        Pregunta(
            "¿Qué capa de la arquitectura Android abstrae las diferencias de hardware entre fabricantes?",
            listOf("Native C/C++ Libraries", "Kernel de Linux", "Hardware Abstraction Layer (HAL)", "Application Framework"),
            2,
            "HAL (Hardware Abstraction Layer) actúa como interfaz estandarizada entre el framework de Android y los drivers específicos de cada fabricante, permitiendo portabilidad."
        ),
        Pregunta(
            "¿Qué estrategia utiliza el 'Low Memory Killer' de Android para liberar memoria?",
            listOf("Swap en almacenamiento", "Terminar procesos menos prioritarios", "Comprimir datos en RAM", "Desfragmentación dinámica"),
            1,
            "El Low Memory Killer (LMK) prioriza procesos según su estado (foreground, visible, service, etc.) y termina los menos críticos cuando el sistema necesita memoria."
        ),

        Pregunta(
            "¿Qué método del ciclo de vida de una Activity se ejecuta cuando esta pierde el foco pero sigue siendo visible parcialmente (ej: al abrir un diálogo)?",
            listOf("onPause()", "onStop()", "onDestroy()", "onResume()"),
            0 ,
            "onPause() se llama cuando la Activity pierde el foco pero sigue visible parcialmente. Es donde se deben pausar animaciones o liberar recursos que no se necesiten en segundo plano."
        ),

        Pregunta(
            "¿Qué atributo de un BroadcastReceiver registrado en el AndroidManifest.xml evita que otras apps lo invoquen?",
            listOf("android:permission", "android:exported=\"false\"", "android:enabled=\"true\"", "android:priority"),
            1 ,
            "android:exported=\"false\" restringe el BroadcastReceiver para que solo pueda ser invocado por componentes de la misma aplicación, mejorando la seguridad."
        ),

        Pregunta(
            "¿Cuál es la principal ventaja de usar ConstraintLayout sobre LinearLayout para diseños complejos?",
            listOf(
                "Permite anidar más niveles de ViewGroups",
                "Reduce la jerarquía de vistas mejorando el rendimiento",
                "Solo permite posicionamiento absoluto",
                "No requiere definir constraints"
            ),
            1,
            "ConstraintLayout permite crear diseños complejos con una jerarquía plana (sin anidamiento excesivo), reduciendo el tiempo de renderizado y mejorando el rendimiento."
        ),

        Pregunta(
            "¿Qué componente de Material Design se coordina con CoordinatorLayout para ocultarse al hacer scroll?",
            listOf("FloatingActionButton", "AppBarLayout", "RecyclerView", "Snackbar"),
            1,
            "AppBarLayout se integra con CoordinatorLayout para implementar patrones de scroll como collapsible toolbars, usando el atributo app:layout_behavior."
        ),

        Pregunta(
            "¿Qué ViewGroup es ideal para mostrar listas largas con reutilización eficiente de vistas?",
            listOf("ListView", "ScrollView", "RecyclerView", "GridView"),
            2,
            "RecyclerView usa el patrón ViewHolder para reutilizar vistas que salen de la pantalla, minimizando el uso de memoria y mejorando el rendimiento en listas largas."
        ),

        Pregunta(
            "¿Por qué Google recomienda usar RecyclerView en lugar de ListActivity en aplicaciones modernas?",
            listOf(
                "Porque ListActivity no soporta ListView",
                "Porque RecyclerView ofrece mejor rendimiento con ViewHolder y LayoutManagers",
                "Porque ListActivity no permite usar Fragments",
                "Porque RecyclerView es obligatorio en Android 12+"
            ),
            1,
            "RecyclerView es más flexible (soporta listas, grids, layouts personalizados) y optimizado (con ViewHolder y LayoutManagers), mientras que ListActivity está obsoleto."
        ),

        Pregunta(
            "¿Qué método de ListActivity se llama automáticamente al hacer clic en un elemento de la lista?",
            listOf("onItemClick()", "onListItemClick()", "onListSelection()", "onClick()"),
            1,
            "onListItemClick() es el callback específico de ListActivity para manejar clics, proporcionando la posición del elemento seleccionado y otros datos relevantes."
        ),

        Pregunta(
            "¿Cuál es la forma RECOMENDADA para que un Fragment comunique un evento a su Activity padre?",
            listOf(
                "Usar getActivity() y llamar a métodos públicos de la Activity",
                "Definir una interfaz en el Fragment e implementarla en la Activity",
                "Usar SharedPreferences",
                "Enviar un BroadcastReceiver"
            ),
            1,
            "Definir una interfaz promueve el bajo acoplamiento: el Fragment solo conoce la interfaz, no la Activity concreta, facilitando pruebas y reutilización."
        ),

        Pregunta(
            "¿Cuál es la principal ventaja de usar LinearLayout con 'layout_weight'?",
            listOf(
                "Permite animaciones complejas",
                "Distribuye el espacio proporcionalmente entre Views",
                "Evita el uso de ConstraintLayout",
                "Soporta Scroll automático"
            ),
            1,
            "layout_weight asigna proporciones de espacio disponible (ej: 70%/30%) independientemente del tamaño de pantalla, ideal para diseños adaptables."
        ),

        Pregunta(
            "¿Qué Layout es ideal para mostrar una lista de 10,000 elementos con reutilización eficiente de Views?",
            listOf("ListView", "ScrollView", "RecyclerView", "GridLayout"),
            2,
            "RecyclerView solo renderiza las vistas visibles en pantalla (+ buffers), reutilizándolas al hacer scroll, lo que lo hace óptimo para grandes conjuntos de datos."
        ),

        Pregunta(
            "¿Cuál es el propósito principal del método 'onMeasure()' en una View personalizada?",
            listOf(
                "Definir las animaciones de la View",
                "Determinar las dimensiones que la View debe ocupar",
                "Manejar eventos táctiles del usuario",
                "Inflar el layout XML de la View"
            ),
            1,
            "RecyclerView solo renderiza las vistas visibles en pantalla (+ buffers), reutilizándolas al hacer scroll, lo que lo hace óptimo para grandes conjuntos de datos."
        ),

        Pregunta(
            "¿Por qué el patrón ViewHolder mejora el rendimiento en RecyclerView?",
            listOf(
                "Porque evita llamadas repetidas a findViewById()",
                "Porque almacena los datos directamente en la View",
                "Porque elimina la necesidad de un Adapter",
                "Porque usa hilos en segundo plano"
            ),
            0,
            "El ViewHolder almacena referencias a las Views del layout del item, evitando buscar repetidamente con findViewById() durante el scroll, lo que es costoso."
        ),

        Pregunta(
            "¿Qué atributo de CardView controla el tamaño de la sombra que proyecta?",
            listOf("app:cardCornerRadius", "app:cardElevation", "android:shadowColor", "app:contentPadding"),
            1,
            "cardElevation define la altura (en dp) de la tarjeta sobre el plano Z, lo que determina el tamaño y suavidad de la sombra según los principios de Material Design."
        ),

        Pregunta(
            "¿Qué método de una View personalizada se debe sobrescribir para dibujar contenido personalizado en el Canvas?",
            listOf("onMeasure()", "onLayout()", "onDraw()", "onAttach()"),
            2,
            "onDraw() recibe un objeto Canvas donde se dibujan formas, texto o imágenes usando Paint. Es llamado cada vez que la View necesita redibujarse."
        ),

        Pregunta(
            "¿Qué componente de RecyclerView es responsable de decidir si los elementos se muestran en una lista vertical, cuadrícula o layout personalizado?",
            listOf("Adapter", "ViewModeler", "LayoutManager", "ItemAnimator"),
            2,
            "El LayoutManager (ej: LinearLayoutManager, GridLayoutManager) controla la disposición de los items, permitiendo diferentes patrones sin modificar el Adapter."
        )
    )

    private var indiceActual = 0
    private var respuestasCorrectas = 0
    private lateinit var timer: CountDownTimer
    private var tiempoRestante = 20 * 60 * 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvPregunta = findViewById<TextView>(R.id.tvPregunta)
        val rgOpciones = findViewById<RadioGroup>(R.id.rgOpciones)
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
        val tvTimer = findViewById<TextView>(R.id.tvTimer)

        fun cargarPregunta() {
            rgOpciones.clearCheck()
            tvFeedback.text = ""
            val pregunta = preguntas[indiceActual]
            tvPregunta.text = pregunta.texto
            rgOpciones.apply {
                for (i in 0 until childCount) {
                    (getChildAt(i) as RadioButton).text = pregunta.opciones[i]
                }
            }
        }

        cargarPregunta()
        iniciarTemporizador(tvTimer)

        btnSiguiente.setOnClickListener {
            val seleccion = rgOpciones.checkedRadioButtonId
            if (seleccion == -1) {
                Toast.makeText(this, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            btnSiguiente.isEnabled = false

            val indiceSeleccionado = rgOpciones.indexOfChild(findViewById(seleccion))
            if (indiceSeleccionado == preguntas[indiceActual].respuestaCorrecta) {
                tvFeedback.setTextColor(getColor(R.color.green))
                tvFeedback.text = "¡Correcto!"
                respuestasCorrectas++
            } else {
                tvFeedback.setTextColor(getColor(R.color.red))
                val correcta = preguntas[indiceActual].respuestaCorrecta
                val textoCorrecto = preguntas[indiceActual].opciones[correcta]
                val explicacion = preguntas[indiceActual].explicacion
                tvFeedback.text = "Incorrecto.\nRespuesta correcta: $textoCorrecto.\n$explicacion"
            }

            Handler(Looper.getMainLooper()).postDelayed({
                indiceActual++

                if (indiceActual < preguntas.size) {
                    cargarPregunta()
                } else {
                    timer.cancel()
                    val intent = Intent(this, ResultadoActivity::class.java)
                    intent.putExtra("puntaje", respuestasCorrectas)

                    val tiempoTotal = (20 * 60 * 1000L) - tiempoRestante
                    intent.putExtra("tiempoTotal", tiempoTotal)

                    startActivity(intent)
                    finish()
                }

                btnSiguiente.isEnabled = true
                rgOpciones.clearCheck()
                tvFeedback.text = ""

            }, 2000)
        }
    }

    private fun iniciarTemporizador(tvTimer: TextView) {
        tiempoRestante = 20 * 60 * 1000L // 20 minutos

        timer = object : CountDownTimer(tiempoRestante, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tiempoRestante = millisUntilFinished
                val minutos = (millisUntilFinished / 1000) / 60
                val segundos = (millisUntilFinished / 1000) % 60

                tvTimer.text = String.format("Tiempo: %02d:%02d", minutos, segundos)

                if (millisUntilFinished <= 2 * 60 * 1000) {
                    tvTimer.setTextColor(getColor(R.color.red))
                } else {
                    tvTimer.setTextColor(getColor(R.color.black))
                }
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "¡Tiempo agotado!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, ResultadoActivity::class.java)
                intent.putExtra("puntaje", respuestasCorrectas)
                val tiempoTotal = (20 * 60 * 1000L) - tiempoRestante
                intent.putExtra("tiempoTotal", tiempoTotal)
                startActivity(intent)
                finish()
            }
        }.start()
    }


    data class Pregunta(
        val texto: String,
        val opciones: List<String>,
        val respuestaCorrecta: Int,
        val explicacion: String)
}