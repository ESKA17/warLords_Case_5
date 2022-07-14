package kz.jusan.tinderformentors.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kz.jusan.tinderformentors.databinding.ActivityLoginBinding
import kz.jusan.tinderformentors.domain.entitites.Login
import kz.jusan.tinderformentors.presentation.MainActivity
import kz.jusan.tinderformentors.presentation.auth.registration.RegistrationActivity

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.successful.observe(this) { successful ->
            if (successful) {
                val editor = getSharedPreferences("MyPreference", MODE_PRIVATE).edit()
                editor.putString("token", loginViewModel.authToken)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, loginViewModel.authToken, Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.tilUsername.editText?.text.toString()
            val loginData = Login(
                email,
                binding.tilPassword.editText?.text.toString()
            )

            val editor = getSharedPreferences("MyPreference", MODE_PRIVATE).edit()
            editor.putString("email", email)
            editor.apply()

            loginViewModel.login(loginData)
        }

        binding.btnRegister.setOnClickListener {
            val registrationIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registrationIntent)
        }
    }

}