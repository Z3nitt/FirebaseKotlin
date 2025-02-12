package com.example.firebaseapp

import ListaMonumentos
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SecondFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locations: List<LatLng>
    private lateinit var monumentos: ArrayList<ListaMonumentos>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val recyclerMonumentosAdapter = view.findViewById<RecyclerView>(R.id.localizaciones)

        monumentos = ListaMonumentos.obtenerMonumentos()
        //COORDS de los sitios
        locations = listOf(
            LatLng(36.71428781117588, -4.414485879308917),   // Farola
            LatLng(36.72013312938797, -4.4155901970036044),  // Banco de España
            LatLng(36.72086054644201, -4.417909646058907),   // Abadia del cister
            LatLng(36.7184624772646, -4.424071240485528),    // Atarazanas
            LatLng(36.72025317871462, -4.41493813944841) ,   // Ayuntamiento
            LatLng(36.72347340932884, -4.4113594474624565) , //Castillo
            LatLng(36.720194011480494, -4.419271267833652) , //Catedral
            LatLng(36.72124537608282, -4.407166044019705) ,  //Cementerio Ingles
            LatLng(36.721182459303265, -4.416801816875072) , //Teatro Romano
            LatLng(36.721157570392386, -4.415808232671027)   //Alcazaba
        )

        recyclerMonumentosAdapter.layoutManager = LinearLayoutManager(context)
        recyclerMonumentosAdapter.adapter = RecyclerMonumentosAdapter(monumentos){ position ->
            moveToLocation(locations[position])//mueve el maps
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 18f)
        mMap.animateCamera(cameraUpdate)
    }
}
