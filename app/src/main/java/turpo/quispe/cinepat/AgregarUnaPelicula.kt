package turpo.quispe.cinepat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class AgregarUnaPelicula : AppCompatActivity() {

    var nombreEditText: EditText?=null
    var Genero_EditText: EditText?=null
    var Fecha_De_Emision_EditText: EditText?=null
    var Calificacion_EditText: EditText?=null
    var Referencia_EditText: EditText?=null
    var LinkImagen_EditText: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_una_pelicula)

        nombreEditText=findViewById(R.id.nombreEditText)
        Genero_EditText=findViewById(R.id.Genero_EditText)
        Fecha_De_Emision_EditText=findViewById(R.id.Fecha_De_Emision_EditText)
        Calificacion_EditText=findViewById(R.id.Calificacion_EditText)
        Referencia_EditText=findViewById(R.id.Referencia_EditText)
        LinkImagen_EditText=findViewById(R.id.LinkImagen_EditText)

        val homeImageView: ImageView = findViewById(R.id.homeImageView)
        homeImageView.setOnClickListener {
            val intent = Intent(this, PantallaInicio::class.java)
            startActivity(intent)
        }
        val menuImageView: ImageView = findViewById(R.id.menuImageView)
        menuImageView.setOnClickListener {
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun clickBtnAgregar(view: View) {
        val url = "https://3b0c-181-176-111-80.ngrok-free.app/api/peliculas"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Película insertada exitosamente", Toast.LENGTH_LONG).show()

                // Enviar la información de la película a PantallaInicio
                val intent = Intent(this, PantallaInicio::class.java)
                intent.putExtra("nombre", nombreEditText?.text.toString())
                intent.putExtra("genero", Genero_EditText?.text.toString())
                intent.putExtra("fecha", Fecha_De_Emision_EditText?.text.toString())
                intent.putExtra("calificacion", Calificacion_EditText?.text.toString())
                intent.putExtra("referencia", Referencia_EditText?.text.toString())
                intent.putExtra("imagen", LinkImagen_EditText?.text.toString())
                startActivity(intent)
            }, Response.ErrorListener { error ->
                error.printStackTrace()
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String, String>()
                parametros["nombre"] = nombreEditText?.text.toString()
                parametros["genero"] = Genero_EditText?.text.toString()
                parametros["fecha"] = Fecha_De_Emision_EditText?.text.toString()
                parametros["calificacion"] = Calificacion_EditText?.text.toString()
                parametros["referencia"] = Referencia_EditText?.text.toString()
                parametros["imagen"] = LinkImagen_EditText?.text.toString()
                return parametros
            }
        }
        queue.add(resultadoPost)
    }

}