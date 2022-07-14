package kz.jusan.tinderformentors.presentation.auth.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kz.jusan.tinderformentors.databinding.ActivityRegistrationBinding
import kz.jusan.tinderformentors.domain.entitites.ConfirmationEmail
import kz.jusan.tinderformentors.domain.entitites.Register
import kz.jusan.tinderformentors.presentation.auth.login.LoginActivity

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrationViewModel.successfulRegistration.observe(this) { successful ->
            if (successful) {
                Toast.makeText(this, registrationViewModel.registrationMessage, Toast.LENGTH_LONG)
                    .show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    registrationViewModel.registrationMessage,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val requestData = Register(
                email = binding.tilEmail.editText?.text.toString(),
                password = binding.tilPassword.editText?.text.toString(),
                role = if (binding.rbMentor.isChecked) 1 else 2, // 0: admin, 1: mentor, 2: mentee
                fullName = binding.tilFullName.editText?.text.toString()
            )
            val emailConfirmData = ConfirmationEmail(
                email = binding.tilEmail.editText?.text.toString()
            )
            registrationViewModel.register(requestData, emailConfirmData)
        }

    }
}