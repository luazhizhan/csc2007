package edu.singaporetech.travelapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import edu.singaporetech.travelapp.databinding.ActivityCurrencyConverterBinding
import edu.singaporetech.travelapp.databinding.ActivityEmailBinding

/**
 * Activity that emails your friend with a message
 */
class EmailActivity : AppCompatActivity() {

    private var binding: ActivityEmailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.previewMessageButton?.setOnClickListener {
            val name = binding?.editTextName?.text.toString()
            val city = binding?.editTextCity?.text.toString()
            val previewMessage = createEmailMessage(name, city)
            binding?.textViewPreview?.text = previewMessage
        }
        binding?.sendEmailButton?.setOnClickListener {
            val name = binding?.editTextName?.text.toString()
            val city = binding?.editTextCity?.text.toString()
            val email = binding?.editTextEmail?.text.toString()
            val emailMessage = createEmailMessage(name, city)
            val emailSubject = "Going on vaction!"
            sendEmail(email, emailSubject, emailMessage)
        }
    }

    /**
     * Call an intent to start the email app
     */
    fun sendEmail(
        email: String,
        emailSubject: String,
        emailMessage: String,
    ) { // do you need to change the parameters?
        Log.d("EmailActivity", "sendEmail: $email, $emailSubject, $emailMessage")
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            putExtra(Intent.EXTRA_TEXT, emailMessage)
        }
        if (intent.resolveActivity(packageManager) != null) {
            intent.setPackage("com.google.android.gm")
            startActivity(intent)
        }
    }

    /**
     * Creates the string to send in the email message
     * @param name
     * @param city
     * @return the email message
     */
    private fun createEmailMessage(name: String, city: String): String {

        val emailMessage: String =
            getString(R.string.hey) + " " + name + " " + getString(R.string.im_going_to) + " " + city + "!"

        return emailMessage
    }

}