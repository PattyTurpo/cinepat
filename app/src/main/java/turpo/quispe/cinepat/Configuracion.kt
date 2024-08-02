package turpo.quispe.cinepat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Configuracion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_configuracion)

        // Configuración del botón de cerrar sesión
        val cerrarSesionButton: Button = findViewById(R.id.Cerrar_Sesion)
        cerrarSesionButton.setOnClickListener {
            cerrarSesion()
        }

        // Configuración del ImageView de inicio
        val homeImageView: ImageView = findViewById(R.id.homeImageView)
        homeImageView.setOnClickListener {
            val intent = Intent(this, PantallaInicio::class.java)
            startActivity(intent)
        }

        // Configuración del ImageView de perfil
        val perfilImageView: ImageView = findViewById(R.id.perfilImageView)
        perfilImageView.setOnClickListener {
            val intent = Intent(this, MiPerfil::class.java)
            startActivity(intent)
        }

        // Configuración de los márgenes de la ventana
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun cerrarSesion() {
        // Limpia el estado de sesión en SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()

        // Redirige al usuario a la pantalla de inicio de sesión
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()  // Finaliza la actividad actual para evitar que el usuario regrese a ella con el botón de atrás
    }
}
