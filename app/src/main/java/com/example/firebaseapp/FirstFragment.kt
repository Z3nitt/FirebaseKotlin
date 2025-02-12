package com.example.firebaseapp

import ListaRecompensas
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.Manifest
import android.util.Log


class FirstFragment : Fragment(), SensorEventListener {

    private lateinit var recompensas: ArrayList<ListaRecompensas>
    private lateinit var sensorManager: SensorManager
    private var stepCounter: Sensor? = null
    private var puntos = 0
    private lateinit var puntosTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        //Inicializar el sensorManager
        sensorManager = requireActivity().getSystemService(SensorManager::class.java)
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        // Obtener el RecyclerView desde el layout del fragmento
        val recyclerViewRecompensas = view.findViewById<RecyclerView>(R.id.recyclerViewRecompensas)

        // Inicializar la lista de recompensas correctamente
        recompensas = ListaRecompensas.obtenerRecompensas()

        // Configurar el RecyclerView
        recyclerViewRecompensas.layoutManager = LinearLayoutManager(context)
        recyclerViewRecompensas.adapter = AdapterRecycler(recompensas)

        //Ref al tv de los puntos
        puntosTextView = view.findViewById(R.id.encabezado)
        actualizarPuntos()

        solicitarPermisos()


        return view
    }

    override fun onResume() {
        super.onResume()
        stepCounter?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.sensor?.type == Sensor.TYPE_STEP_DETECTOR) {
            puntos += 1  // Sumar un punto por cada paso detectado
            actualizarPuntos()
            Log.d("StepDetector", "funca: $puntos")  // Ver en Logcat
        }
    }

    private fun actualizarPuntos() {
        puntosTextView.text = "Cantidad de puntos: $puntos"
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    private fun solicitarPermisos() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 1)
        }
    }
}
