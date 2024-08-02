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

        // Comprobar si hay datos de una nueva película
        val nombre = intent.getStringExtra("nombre")
        val genero = intent.getStringExtra("genero")
        val fecha = intent.getStringExtra("fecha")
        val calificacion = intent.getStringExtra("calificacion")
        val referencia = intent.getStringExtra("referencia")
        val imagen = intent.getStringExtra("imagen")

        if (nombre != null && genero != null && fecha != null && calificacion != null && referencia != null && imagen != null) {
            agregarFilaPelicula(nombre, genero, fecha, calificacion, referencia, imagen)
        }

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

    // Función para agregar una fila a la tabla
    fun agregarFilaPelicula(nombre: String, genero: String, fecha: String, calificacion: String, referencia: String, imagen: String) {
        val inflater = LayoutInflater.from(this)
        val row = inflater.inflate(R.layout.tabla_row_np, null) as TableRow

        val nombreTextView = row.findViewById<TextView>(R.id.nombreEditText)
        val generoTextView = row.findViewById<TextView>(R.id.Genero_EditText)
        val fechaTextView = row.findViewById<TextView>(R.id.Fecha_De_Emision_EditText)
        val calificacionTextView = row.findViewById<TextView>(R.id.Calificacion_EditText)
        val referenciaTextView = row.findViewById<TextView>(R.id.Referencia_EditText)
        val imagenImageView = row.findViewById<ImageView>(R.id.imageFilterView)

        nombreTextView.text = nombre
        generoTextView.text = genero
        fechaTextView.text = fecha
        calificacionTextView.text = calificacion
        referenciaTextView.text = referencia
        Glide.with(this).load(imagen).into(imagenImageView)

        tbPeliculas?.addView(row)
    }


}
