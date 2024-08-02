package turpo.quispe.cinepat

import android.provider.ContactsContract.CommonDataKinds.Photo
import java.util.concurrent.Flow.Publisher

data class Peliculas (
    val pelicula:String,
    val publisher:String,
    val realName:String,
    val photo: String
)