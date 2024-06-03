package turpo.quispe.cinepat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView

class PantallaInicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pantalla_inicio)


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
        val generobtn: Button =findViewById(R.id.genero)
        generobtn.setOnClickListener {
            val intent: Intent = Intent(this, PeliculasPorGenero::class.java)
            startActivity(intent)
        }

        val fechaDeEmisionbtn: Button =findViewById(R.id.fecha_de_emision)
        fechaDeEmisionbtn.setOnClickListener {
            val intent: Intent = Intent(this, PeliculasPorFechaDeEmision::class.java)
            startActivity(intent)
        }

        val favoritosbtn: Button =findViewById(R.id.favoritos)
        favoritosbtn.setOnClickListener {
            val intent: Intent = Intent(this, PeliculasPorFavoritos::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}