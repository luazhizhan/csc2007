package edu.singaporetech.vaccinereg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.singaporetech.vaccinereg.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get intent data
        val nric = intent.getStringExtra("nric")
        val date = intent.getStringExtra("date")
        val centre = intent.getStringExtra("centre")
        val address = intent.getStringExtra("address")

        // if nric or date or centre or address is null
        if (nric == null || date == null || centre == null || address == null) {
            finish()
        }

        // set text
        binding.textViewNRIC.text = nric
        binding.textViewBookingDate.text = date
        binding.textViewCentre.text = centre
        binding.textViewCentreAddress.text = address
    }
}