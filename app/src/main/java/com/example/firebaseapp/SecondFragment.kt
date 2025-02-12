package com.example.firebaseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SecondFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var spinner: Spinner
    private lateinit var locations: List<LatLng>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuración de las ubicaciones
        locations = listOf(
            LatLng(-34.71528, -58.40778), // Lanús
            LatLng(40.7128, -74.0060),   // New York
            LatLng(48.8566, 2.3522),     // Paris
            LatLng(51.5074, -0.1278),    // Londres
            LatLng(35.6895, 139.6917)    // Tokio
        )

        // Configurar el Spinner
        spinner = view.findViewById(R.id.locations_spinner)
        val locationNames = listOf("Lanús", "New York", "Paris", "Londres", "Tokio")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locationNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Manejar la selección del Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Cambiar la ubicación del mapa
                moveToLocation(locations[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada si no se selecciona nada
            }
        }

        // Obtener el SupportMapFragment y ser notificado cuando el mapa esté listo para ser usado
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Añadir un marcador en la primera ubicación y mover la cámara
        moveToLocation(locations[0])
    }

    // Función para mover la cámara a la ubicación seleccionada
    private fun moveToLocation(location: LatLng) {
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 10f)
        mMap.animateCamera(cameraUpdate)
    }
}
