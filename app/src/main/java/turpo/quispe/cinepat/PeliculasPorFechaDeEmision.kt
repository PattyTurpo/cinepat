package turpo.quispe.cinepat

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PeliculasPorFechaDeEmision : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_peliculas_por_fecha_de_emision)
        val menuImageView: ImageView = findViewById(R.id.menuImageView)
        menuImageView.setOnClickListener {
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }
        val homeImageView: ImageView = findViewById(R.id.homeImageView)
        homeImageView.setOnClickListener {
            val intent = Intent(this, PantallaInicio::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}