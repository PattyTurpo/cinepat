package turpo.quispe.cinepat

import android.app.DatePickerDialog
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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.Calendar

class AgregarUnaPelicula : AppCompatActivity() {

    private var nombreEditText: EditText? = null
    private var generoEditText: EditText? = null
    private var fechaDeEmisionEditText: EditText? = null
    private var calificacionEditText: EditText? = null
    private var referenciaEditText: EditText? = null
    private var linkImagenEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_una_pelicula)

        nombreEditText = findViewById(R.id.nombreEditText)
        generoEditText = findViewById(R.id.Genero_EditText)
        fechaDeEmisionEditText = findViewById(R.id.Fecha_De_Emision_EditText)
        calificacionEditText = findViewById(R.id.Calificacion_EditText)
        referenciaEditText = findViewById(R.id.Referencia_EditText)
        linkImagenEditText = findViewById(R.id.LinkImagen_EditText)

        fechaDeEmisionEditText?.setOnClickListener {
            showDatePickerDialog()
        }

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

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                fechaDeEmisionEditText?.setText(selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }

    fun clickBtnAgregar(view: View) {
        val url = "https://3b0c-181-176-111-80.ngrok-free.app/api/peliculas"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            { response ->
                Toast.makeText(this, "Película insertada exitosamente", Toast.LENGTH_LONG).show()
            },
            { error ->
                error.printStackTrace()
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val parametros = HashMap<String, String>()
                parametros["nombre"] = nombreEditText?.text.toString()
                parametros["genero"] = generoEditText?.text.toString()
                parametros["fecha"] = fechaDeEmisionEditText?.text.toString()
                parametros["calificacion"] = calificacionEditText?.text.toString()
                parametros["referencia"] = referenciaEditText?.text.toString()
                parametros["imagen"] = linkImagenEditText?.text.toString()
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}
