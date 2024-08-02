package turpo.quispe.cinepat

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MiPerfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_perfil)

        val imageView: ImageView = findViewById(R.id.imageView133)
        imageView.setOnClickListener {
            val intent = Intent(this, AgregarUnaPelicula::class.java)
            startActivity(intent)
        }
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
        val meImageView: ImageView = findViewById(R.id.imageView11)
        meImageView.setOnClickListener {
            val intent = Intent(this, PeliculaReferencia2::class.java)
            startActivity(intent)
        }
        val meImageView1: ImageView = findViewById(R.id.imageView14)
        meImageView1.setOnClickListener {
            val intent = Intent(this, PantallaReferencia10::class.java)
            startActivity(intent)
        }
        val meImageView2: ImageView = findViewById(R.id.imageView13)
        meImageView2.setOnClickListener {
            val intent = Intent(this, PeliculaReferencia3::class.java)
            startActivity(intent)
        }
        val meImageView3: ImageView = findViewById(R.id.imageView111)
        meImageView3.setOnClickListener {
            val intent = Intent(this, PeliculaReferencia::class.java)
            startActivity(intent)
        }
        val meImageView4: ImageView = findViewById(R.id.imageView144)
        meImageView4.setOnClickListener {
            val intent = Intent(this, PeliculaReferencia6::class.java)
            startActivity(intent)
        }
        val meImageView6: ImageView = findViewById(R.id.imageView1111)
        meImageView6.setOnClickListener {
            val intent = Intent(this, PantallaReferencia10::class.java)
            startActivity(intent)
        }
        val meImageView7: ImageView = findViewById(R.id.imageView1444)
        meImageView7.setOnClickListener {
            val intent = Intent(this, PeliculasHachiko::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}