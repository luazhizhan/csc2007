package edu.singaporetech.madata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import edu.singaporetech.madata.databinding.ActivityListBinding
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val userPreferencesRepository by lazy { UserPreferencesRepository.getInstance(this) }
    private lateinit var viewModel: FourDigitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[FourDigitViewModel::class.java]

        val adapter = FourDigitListAdapter(listOf())
        binding.listViewFourDigits.adapter = adapter

        viewModel.allFourDigits.observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        binding.listViewFourDigits.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        lifecycleScope.launch {
            userPreferencesRepository.layoutFlow.collect {
                if (it.layout == "GRID") {
                    binding.listViewFourDigits.layoutManager =
                        GridLayoutManager(this@ListActivity, 4)
                    binding.switchGrid.isChecked = true
                } else {
                    binding.listViewFourDigits.layoutManager =
                        LinearLayoutManager(this@ListActivity)
                    binding.switchGrid.isChecked = false
                }
            }
        }

        binding.resetDataButton.setOnClickListener {
            viewModel.deleteAll()
            adapter.notifyDataSetChanged()
        }

        binding.switchGrid.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                if (isChecked) {
                    binding.listViewFourDigits.layoutManager =
                        GridLayoutManager(this@ListActivity, 4)
                    userPreferencesRepository.storeLayout("GRID")
                } else {
                    binding.listViewFourDigits.layoutManager =
                        LinearLayoutManager(this@ListActivity)
                    userPreferencesRepository.storeLayout("LINEAR")
                }
            }
        }
    }
}