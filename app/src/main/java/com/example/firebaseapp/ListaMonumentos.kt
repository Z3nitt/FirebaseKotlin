import com.example.firebaseapp.R

data class ListaMonumentos (
    val idImagen: Int,
    val nombre: String,
    val descripcion: String = ""

){
    companion object{
        fun obtenerMonumentos(): ArrayList<ListaMonumentos>{
            return arrayListOf(
                ListaMonumentos(R.drawable.farola, "Farola", "Emblemático faro de Málaga, construido en 1817, que aún guía a los navegantes en el puerto."),
                ListaMonumentos(R.drawable.banco, "Banco de España", "Majestuoso edificio neoclásico de 1936, sede de instituciones financieras y administrativas."),
                ListaMonumentos(R.drawable.abadiacister, "Abadia del Cister", "Monasterio del siglo XIII con una arquitectura sobria y un ambiente de paz y recogimiento."),
                ListaMonumentos(R.drawable.atarazanas, "Mercado Atarazanas", "Antiguo astillero nazarí convertido en un vibrante mercado gastronómico con una impresionante vidriera."),
                ListaMonumentos(R.drawable.ayuntamiento, "Ayundamiento", "Edificio de estilo neobarroco inaugurado en 1919, símbolo del gobierno municipal de Málaga."),
                ListaMonumentos(R.drawable.castillogibralfaro, "Castillo Gibralfaro", "Fortaleza del siglo XIV con vistas panorámicas, construida para proteger la Alcazaba."),
                ListaMonumentos(R.drawable.catedral, "Catedral", "Impresionante templo renacentista apodado \"La Manquita\" por su torre inacabada."),
                ListaMonumentos(R.drawable.cementerioingles, "Cementerio Ingles", "Primer cementerio protestante de España, con tumbas históricas en un entorno romántico."),
                ListaMonumentos(R.drawable.teatroromano, "Teatro Romano", "Ruinas del siglo I a.C., testimonio de la presencia romana en la antigua Malaca."),
                ListaMonumentos(R.drawable.alcazaba, "Alcazaba", "Palacio-fortaleza musulmán del siglo XI con murallas imponentes y jardines de influencia andalusí.")
            )
        }
    }
}