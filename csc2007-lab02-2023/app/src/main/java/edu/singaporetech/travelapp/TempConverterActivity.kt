package edu.singaporetech.travelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.singaporetech.travelapp.databinding.ActivityCurrencyConverterBinding
import edu.singaporetech.travelapp.databinding.ActivityTempConverterBinding
import java.math.RoundingMode
import java.text.DecimalFormat


/**
 * Activity that displays UI to convert temperature
 */
class TempConverterActivity : AppCompatActivity() {

    private var binding: ActivityTempConverterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTempConverterBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.convertButton?.setOnClickListener {
            val value = binding?.editTextTemp?.text.toString().toFloat()
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.DOWN

            if (binding?.fahrenheitRadioButton?.isChecked == true) {
                val convertedValue = convertCelsiusToFahrenheit(value)
                val roundoff = df.format(convertedValue)
                binding?.textViewConvert?.text =
                    value.toString() + " Celsius is " + roundoff.toString() + " Fahrenheit"
            }
            if (binding?.celsiusRadioButton?.isChecked == true) {
                val convertedValue = convertFahrenheitToCelsius(value)
                val roundoff = df.format(convertedValue)
                binding?.textViewConvert?.text =
                    value.toString() + " Fahrenheit is " + roundoff.toString() + " Celsius"
            }
        }

        binding?.clearButton?.setOnClickListener {
            binding?.editTextTemp?.setText("")
            binding?.textViewConvert?.text = ""
        }
    }

    /**
     * Converts fahrenheit to celsius
     * @param fahrenheit temperature in f
     * @return
     */
    private fun convertFahrenheitToCelsius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9
    }

    /**
     * Converts celsius to fahrenheit
     * @param celsius temperature in c
     * @return
     */
    fun convertCelsiusToFahrenheit(celsius: Float): Float {
        return celsius * 9 / 5 + 32
    }

}