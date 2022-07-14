package kz.jusan.tinderformentors.presentation.auth.registration

import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kz.jusan.tinderformentors.R
import kz.jusan.tinderformentors.databinding.ActivityRegistrationDetailsBinding
import kz.jusan.tinderformentors.domain.entitites.Majors
import kz.jusan.tinderformentors.domain.entitites.RegistrationDetails
import kz.jusan.tinderformentors.presentation.MainActivity
import kz.jusan.tinderformentors.presentation.auth.login.LoginActivity
import kz.jusan.tinderformentors.util.Constants.NIS_SCHOOLS
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RegistrationDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationDetailsBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()
    var selectedMajors = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSchoolsSpinner()
        setupDateOfBirthPicker()

        registrationViewModel.successfulRegistrationDetails.observe(this) { successful ->
            if (successful) {
                Toast.makeText(this, registrationViewModel.registrationDetailsMessage, Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, registrationViewModel.registrationDetailsMessage, Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnSubmitData.setOnClickListener {
            val registrationDetailsData = RegistrationDetails(
                city = binding.tilCity.editText?.text.toString(),
                school = binding.tilSchool.editText?.text.toString(),
                phoneNumber = binding.tilPhone.editText?.text.toString(),
                university = binding.tilUniversity.editText?.text.toString(),
                dateOfBirth = binding.tilDateOfBirth.editText?.text.toString(),
                iin = binding.tilIdNumber.editText?.text.toString()
            )
            val majors = Majors(
                majors = selectedMajors
            )
            registrationViewModel.submitRegistrationDetails(getSavedAuthToken(), registrationDetailsData)
            registrationViewModel.submitMajorsList(getSavedAuthToken(), majors)
        }

    }

    private fun setupSchoolsSpinner() {
        val adapter = ArrayAdapter(
            this,
            R.layout.dropdown_menu_popup_item,
            R.id.tvSchoolName,
            NIS_SCHOOLS
        )
        binding.atvSchool.setAdapter(adapter)
    }

    private fun setupDateOfBirthPicker() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        val myCalendar = Calendar.getInstance();

        val dateFromListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            binding.tietDateOfBirth.setText(dateFormat.format(myCalendar.time))
        }

        binding.tietDateOfBirth.setOnClickListener {
            DatePickerDialog(
                this,
                R.style.MyDatePickerDialogTheme,
                dateFromListener,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.cbMath -> {
                    if (checked) {
                        selectedMajors.add(0)
                    } else {
                        // Remove the meat
                    }
                }
                R.id.cbPhysics -> {
                    if (checked) {
                        selectedMajors.add(1)
                    } else {
                        // I'm lactose intolerant
                    }
                }
                R.id.cbChemistry -> {
                    if (checked) {
                        selectedMajors.add(2)
                    } else {
                        // I'm lactose intolerant
                    }
                }
                R.id.cbBiology -> {
                    if (checked) {
                        selectedMajors.add(3)
                    } else {
                        // I'm lactose intolerant
                    }
                }
                R.id.cbInformatics -> {
                    if (checked) {
                        selectedMajors.add(4)
                    } else {
                        // I'm lactose intolerant
                    }
                }
                R.id.cbHistory -> {
                    if (checked) {
                        selectedMajors.add(5)
                    } else {
                        // I'm lactose intolerant
                    }
                }
            }

        }
    }

    private fun getSavedAuthToken(): String {
        val prefs: SharedPreferences = getSharedPreferences("MyPreference", MODE_PRIVATE)
        return prefs.getString("token", "ERROR") ?: "ERROR"
    }
}