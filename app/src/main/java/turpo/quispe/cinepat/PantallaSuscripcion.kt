package turpo.quispe.cinepat

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PantallaSuscripcion : AppCompatActivity() {

    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_suscripcion)

        dbHelper = UserDatabaseHelper(this)

        val nombresEditText = findViewById<EditText>(R.id.nombresEditText)
        val apellidosEditText = findViewById<EditText>(R.id.ApellidosEditText)
        val fechaDeNacimientoEditText = findViewById<EditText>(R.id.Fecha_De_Nacimiento_EditText)
        val correoElectronicoEditText = findViewById<EditText>(R.id.Correo_Electronico_EditText)
        val contraseñaEditText = findViewById<EditText>(R.id.Contraseña_EditText)
        val registrarButton = findViewById<Button>(R.id.loginButton)

        registrarButton.setOnClickListener {
            val nombres = nombresEditText.text.toString()
            val apellidos = apellidosEditText.text.toString()
            val fechaDeNacimiento = fechaDeNacimientoEditText.text.toString()
            val correoElectronico = correoElectronicoEditText.text.toString()
            val contraseña = contraseñaEditText.text.toString()

            if (nombres.isEmpty() || apellidos.isEmpty() || fechaDeNacimiento.isEmpty() || correoElectronico.isEmpty() || contraseña.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            } else {
                val userId = dbHelper.addUser(nombres, apellidos, fechaDeNacimiento, correoElectronico, contraseña)
                if (userId > -1) {
                    val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                    Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
