package edu.singaporetech.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.singaporetech.travelapp.databinding.ActivityMainBinding

/**
 * Lab 02: Travel App
 * Main Screen
 *
 * 2020-01-27: port to kotlin (jeannie)
 * 2023-01-18: fixes for latest platform level
 */
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.temperatureConverterButton?.setOnClickListener {
            val intent = Intent(this@MainActivity, TempConverterActivity::class.java)
            startActivity(intent)
        }
        binding?.currencyConverterButton?.setOnClickListener {
            val intent = Intent(this@MainActivity, CurrencyConverterActivity::class.java)
            startActivity(intent)
        }

        binding?.emailFriendButton?.setOnClickListener {
            val intent = Intent(this@MainActivity, EmailActivity::class.java)
            startActivity(intent)
        }
    }
}