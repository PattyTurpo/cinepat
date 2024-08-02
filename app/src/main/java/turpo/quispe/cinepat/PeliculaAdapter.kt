package turpo.quispe.cinepat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONObject

class PeliculaAdapter(private val peliculas: List<JSONObject>) :
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val generoTextView: TextView = view.findViewById(R.id.generoTextView)
        val fechaTextView: TextView = view.findViewById(R.id.fechaTextView)
        val referenciaTextView: TextView = view.findViewById(R.id.referenciaTextView)
        val calificacionTextView: TextView = view.findViewById(R.id.calificacionTextView)
        val imageFilterView: ImageView = view.findViewById(R.id.imageFilterView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = peliculas[position]
        holder.nombreTextView.text = pelicula.getString("nombre")
        holder.generoTextView.text = pelicula.getString("genero")
        holder.fechaTextView.text = pelicula.getString("fecha")
        holder.referenciaTextView.text = pelicula.getString("referencia")
        holder.calificacionTextView.text = pelicula.getString("calificacion")

        // Cargar imagen con Glide
        val imageUrl = pelicula.getString("imagen")
        Glide.with(holder.itemView.context).load(imageUrl).into(holder.imageFilterView)
    }

    override fun getItemCount(): Int = peliculas.size
}
