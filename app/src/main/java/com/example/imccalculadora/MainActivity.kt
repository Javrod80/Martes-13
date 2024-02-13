package com.example.imccalculadora


import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMC : AppCompatActivity() {

    private var resualtura: Int = 100
    private var resupeso: Int = 10


    private lateinit var varcms: TextView
    private lateinit var progressBar: RangeSlider
    private lateinit var btn1: FloatingActionButton
    private lateinit var btn2: FloatingActionButton
    private lateinit var varpeso: TextView
    private lateinit var barra: AppCompatButton
    private lateinit var resultado: TextView
    private lateinit var descrip: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniView()
        listerer()
        variarPeso()


    }


    fun iniView() {
        varcms = findViewById(R.id.varcms)
        progressBar = findViewById(R.id.progressBar)
        varpeso = findViewById(R.id.varpeso)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        barra = findViewById(R.id.barra)
        resultado = findViewById(R.id.resultado)
        descrip = findViewById(R.id.descrip)

    }

    private fun listerer() {
        progressBar.addOnChangeListener { _, value, _ ->
            resualtura = value.toInt()
            variarAltura()
        }

        btn1.setOnClickListener {
            resupeso -= 1
            variarPeso()
        }
        btn2.setOnClickListener {
            resupeso += 1
            variarPeso()
        }
        barra.setOnClickListener {
            val imc: Float = calcularImc()
            val desc = descrip(imc)

            val df = DecimalFormat("#.##")
            resultado.text = "El imc es ${df.format(imc)}"
            descrip.text = desc
        }
    }

    private fun variarPeso() {
        varpeso.text = "$resupeso KG"
    }

    private fun variarAltura() {
        varcms.text = "$resualtura cms"
    }


    private fun calcularImc(): Float {

        val imc: Float =
            resupeso.toFloat() / ((resualtura.toFloat() / 100) * (resualtura.toFloat() / 100))
        return imc
    }

    private fun descrip(imc: Float) :String {
        val descripcion:String = when (imc) {
            in 1.0..20.0 -> "Bajo peso"

            in 20.0..25.0 -> "Peso normal"

            in 25.0..30.0 -> "Sobrepeso"

            in 30.0..35.0 -> "Obesidad tipo 1"

            in 35.0..40.0 -> "Obesidad tipo 2"

            else -> "Obesidad tipo 3"
        }
        return descripcion
    }
}


