import com.example.firebaseapp.R

data class ListaRecompensas(
    val idImagen: Int,
    val textoSuperior: String,
    val textoInferior: String,
    val texto: String
) {
    companion object {
        fun obtenerRecompensas(): ArrayList<ListaRecompensas> {
            return arrayListOf(
                ListaRecompensas(R.drawable.spotify, "Suscripción a Spotify Premium 5 meses", "Puntos requeridos: 5000", ""),
                ListaRecompensas(R.drawable.amazongiftcard, "Tarjeta regalo de Amazon de 25 €", "Puntos requeridos: 50000", ""),
                ListaRecompensas(R.drawable.cinemaimage, "Dos entradas gratis para ir al cine", "Puntos requeridos: 7500", ""),
                ListaRecompensas(R.drawable.hbo, "Membresía de 3 meses a HBO", "Puntos requeridos: 10000", ""),
                ListaRecompensas(R.drawable.auricularesbluetooth, "Auriculares inalámbricos", "Puntos requeridos: 1000000", ""),
                ListaRecompensas(R.drawable.tecladomecanico, "Teclado mecánico", "Puntos requeridos: 5000000", ""),
                ListaRecompensas(R.drawable.camiseta, "Camiseta gratis", "Puntos requeridos: 15000", ""),
                ListaRecompensas(R.drawable.cursopng, "Curso gratis a libre elección", "Puntos requeridos: 70000", "")
            )
        }
    }
}
