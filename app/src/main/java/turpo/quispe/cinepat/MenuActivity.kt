package turpo.quispe.cinepat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            navigateToInicio()
        }

        dbHelper = UserDatabaseHelper(this)

        val btn: Button = findViewById(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        btn.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString().trim()
            val storedPassword = dbHelper.getPasswordByEmail(email)

            if (storedPassword != null && storedPassword == password) {
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                navigateToInicio()
            } else {
                Toast.makeText(this, "Email o contraseña incorrecta", Toast.LENGTH_LONG).show()
            }
        }

        val registrar = findViewById<TextView>(R.id.registrarTextView)
        registrar.setOnClickListener {
            registrar()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun registrar() {
        val intent = Intent(this, PantallaSuscripcion::class.java)
        startActivity(intent)
    }

    private fun navigateToInicio() {
        val intent = Intent(this, PantallaInicio::class.java)
        startActivity(intent)
        finish()
    }
}
