package edu.singaporetech.vaccinereg

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import edu.singaporetech.vaccinereg.databinding.ActivityMainBinding
import java.util.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the intent data
        val nric = intent.getStringExtra("nric")
        // if nric is null
        if (nric == null) {
            // go back to registration page
            finish()
        }

        // Set welcome message
        binding.textViewWelcome.text = "Welcome, " + nric;

        // Set today's date
        val formatter = SimpleDateFormat("dd/MM/YYYY")
        val date = Date()
        val current = formatter.format(date)
        binding.editTextDate.setText(current.toString())

        binding.buttonBookAppointment.setOnClickListener {
            // Get the data
            val date = binding.editTextDate.text.toString()
            val centre = binding.spinnerVaccinationCentre.selectedItem.toString()
            val address = binding.textViewAddress.text.toString()

            // Create an intent
            // Pass the data to the next activity
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra("nric", nric)
            intent.putExtra("date", date)
            intent.putExtra("centre", centre)
            intent.putExtra("address", address)
            startActivity(intent)
        }

        // Populate spinner
        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            Constant.vaccinationCentres
        )
        binding.spinnerVaccinationCentre.adapter = adapter

        // Set address
        binding.textViewAddress.text =
            Constant.getAddress(binding.spinnerVaccinationCentre.selectedItem.toString())

        // Set listener
        binding.spinnerVaccinationCentre.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    binding.textViewAddress.text = Constant.getAddressByIndex(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    binding.textViewAddress.text = ""
                }
            }

    }
}