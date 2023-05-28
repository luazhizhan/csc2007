package edu.singaporetech.vaccinereg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.singaporetech.vaccinereg.databinding.ActivityRegBinding

/**
 *  CSC2007 Quiz 1
 *  Implementation of registration activity
 */
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetStarted.setOnClickListener {
            val smsCode = binding.editTextSMSCode.text.toString()
            val nric = binding.editTextNRIC.text.toString()

            // Check if smsCode and nric are valid
            if (isSMSCodeValid(smsCode) == false) {
                binding.textViewError.text = "Please enter a valid code"
            } else if (isNRICValid(nric) == false) {
                binding.textViewError.text = "Please enter a valid NRIC"
            } else if (isSMSCodeValid(smsCode) && isNRICValid(nric)) {
                // Go to main activity with nric as intent data
                val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                intent.putExtra("nric", nric)
                startActivity(intent)
            }
        }

        // Clear button click listener
        binding.buttonClear.setOnClickListener {
            binding.editTextSMSCode.text.clear()
            binding.editTextNRIC.text.clear()
        }

    }


    fun isSMSCodeValid(smsCode: String): Boolean {
        val length = smsCode.length
        if (length != 10) return false

        // check if it has a number and a character
        var hasNumber = false
        var hasCharacter = false
        for (i in 0 until length) {
            if (smsCode[i].isDigit()) {
                hasNumber = true
            } else if (smsCode[i].isLetter()) {
                hasCharacter = true
            }
            if (hasNumber && hasCharacter) {
                break
            }
        }

        return hasNumber && hasCharacter
    }

    fun isNRICValid(nricNumber: String): Boolean {
        val length = nricNumber.length
        if (length != 9) return false

        // check if first and last character is a letter
        if (nricNumber[0].isLetter() == false || nricNumber[length - 1].isLetter() == false) {
            return false
        }

        // check if the rest are digits
        var has7Digits = true
        for (i in 1 until length - 1) {
            if (nricNumber[i].isLetter()) {
                has7Digits = false
                break
            }
        }
        return has7Digits
    }
}