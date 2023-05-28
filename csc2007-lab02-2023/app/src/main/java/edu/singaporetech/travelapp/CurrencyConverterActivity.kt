package edu.singaporetech.travelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.singaporetech.travelapp.databinding.ActivityCurrencyConverterBinding
import edu.singaporetech.travelapp.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Activity that displays UI to convert currency
 */
class CurrencyConverterActivity : AppCompatActivity() {
    private var binding: ActivityCurrencyConverterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyConverterBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.convertButton?.setOnClickListener {
            val value = binding?.editTextSingDollar?.text.toString().toFloat()
            val exchangeRate = binding?.editTextRate?.text.toString().toFloat()
            val result = calculateRate(value, exchangeRate)
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            val roundoff = df.format(result)

            binding?.textViewConvert?.text =
                value.toString() + " SGD is " + roundoff.toString() + " " + binding?.editTextCurrency?.text.toString()
        }
    }

    /**
     * Formula to calculate the destination currency
     * @param value
     * @param exchangeRate
     * @return
     */
    private fun calculateRate(value: Float, exchangeRate: Float): Float {
        return value * exchangeRate
    }

}