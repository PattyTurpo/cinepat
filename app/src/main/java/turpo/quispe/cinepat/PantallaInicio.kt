package turpo.quispe.cinepat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONException

class PantallaInicio : AppCompatActivity() {

    var tbPeliculas: TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_inicio)

        tbPeliculas = findViewById(R.id.tbPeliculas)
        tbPeliculas?.removeAllViews()

        val queue = Volley.newRequestQueue(this)
        val url = "https://3b0c-181-176-111-80.ngrok-free.app/api/peliculas"

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val pelicula = LayoutInflater.from(this).inflate(R.layout.tabla_row_np, null) as TableRow
                        val nombreEditText = pelicula.findViewById<TextView>(R.id.nombreEditText)
                        val Genero_EditText = pelicula.findViewById<TextView>(R.id.Genero_EditText)
                        val Fecha_De_Emision_EditText = pelicula.findViewById<TextView>(R.id.Fecha_De_Emision_EditText)
                        val Referencia_EditText = pelicula.findViewById<TextView>(R.id.Referencia_EditText)
                        val Calificacion_EditText = pelicula.findViewById<TextView>(R.id.Calificacion_EditText)
                        // Si tienes una imagen para mostrar, puedes usar una biblioteca como Glide o Picasso aquí
                        val imageFilterView = pelicula.findViewById<ImageView>(R.id.imageFilterView)

                        nombreEditText.text = jsonObject.getString("nombre")
                        Genero_EditText.text = jsonObject.getString("genero")



                        Fecha_De_Emision_EditText.text = jsonObject.getString("fecha")
                        Referencia_EditText.text = jsonObject.getString("referencia")
                        Calificacion_EditText.text = jsonObject.getString("calificacion")
                        // Cargar imagen con Glide o Picasso
                        val imageUrl = jsonObject.getString("imagen")
                        Glide.with(this).load(imageUrl).into(imageFilterView)

                        tbPeliculas?.addView(pelicula)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        )
        queue.add(jsonArrayRequest)

        val menuImageView: ImageView = findViewById(R.id.menuImageView)
        menuImageView.setOnClickListener {
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }

        val imageView: ImageView = findViewById(R.id.perfilImageView)
        imageView.setOnClickListener {
            val intent = Intent(this, MiPerfil::class.java)
            startActivity(intent)
        }

        val generobtn: Button = findViewById(R.id.genero)
        generobtn.setOnClickListener {
            val intent = Intent(this, PeliculasPorGenero::class.java)
            startActivity(intent)
        }

        val fechaDeEmisionbtn: Button = findViewById(R.id.fecha_de_emision)
        fechaDeEmisionbtn.setOnClickListener {
            val intent = Intent(this, PeliculasPorFechaDeEmision::class.java)
            startActivity(intent)
        }

        val favoritosbtn: Button = findViewById(R.id.favoritos)
        favoritosbtn.setOnClickListener {
            val intent = Intent(this, PeliculasPorFavoritos::class.java)
            startActivity(intent)
        }

        val peliculareferencia3: ConstraintLayout = findViewById(R.id.imageView6)
        peliculareferencia3.setOnClickListener {
            val intent = Intent(this, PeliculaReferencia3::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
