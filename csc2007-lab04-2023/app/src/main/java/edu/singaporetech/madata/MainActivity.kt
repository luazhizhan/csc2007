package edu.singaporetech.madata

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import edu.singaporetech.madata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FourDigitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[FourDigitViewModel::class.java]

        viewModel.latestFourDigits.observe(this) {
            binding.textViewFourDigitNum.text = if (it.isNotEmpty()) it[0].value else "0000"
        }

        binding.generateButton.setOnClickListener {
            val fourDigit = Utils.generateRandomFourDigitNumber()
            viewModel.add(fourDigit)
            binding.textViewFourDigitNum.text = fourDigit.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionDigitList -> {
                val intent = Intent(this@MainActivity, ListActivity::class.java)
                startActivity(intent)

            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }
}