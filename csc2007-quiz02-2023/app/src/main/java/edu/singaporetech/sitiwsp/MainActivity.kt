package edu.singaporetech.sitiwsp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.Preferences
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.singaporetech.sitiwsp.databinding.ActivityMainBinding
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.work.impl.model.Preference
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userPreferencesRepository by lazy { UserPreferencesRepository.getInstance(this) }
    private lateinit var rightListAdapter: RightListAdapter
    private lateinit var leftList: List<Job>
    private var isASC = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // initialize left and right list
        leftList = Constant.jobs
        var rightList: List<Job> = listOf()

        rightListAdapter = RightListAdapter(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                runBlocking {
                    // read from data store
                    val userPreferences = userPreferencesRepository.IWSPOptionsFlow.first()
                    val IWSPOptions = userPreferences.IWSPOptions
                    val item = rightList[position]

                    // parse string to json array
                    val gson = Gson()
                    val jsonData = IWSPOptions ?: "[]"
                    val json = gson.fromJson(jsonData, JsonArray::class.java).asJsonArray

                    // remove from json if company exists
                    val updatedJson = JsonArray()
                    for (element in json) {
                        val currentItem = element.asString
                        if (currentItem != item.company) {
                            updatedJson.add(element)
                        }
                    }

                    // update data store
                    val jsonStr = gson.toJson(updatedJson)
                    userPreferencesRepository.storeIWSPOptions(jsonStr)

                    // map to List<Job> and update right list
                    rightList = updatedJson.map {
                        val company = it.asString
                        Constant.getJobByCompanyName(company)
                    }

                    rightListAdapter.items = rightList
                    rightListAdapter.notifyItemRemoved(position)
                }
            }
        }, rightList)
        binding.recyclerviewright.adapter = rightListAdapter
        binding.recyclerviewright.layoutManager =
            LinearLayoutManager(this@MainActivity)

        lifecycleScope.launch {
            // read from data store
            val userPreferences = userPreferencesRepository.IWSPOptionsFlow.first()
            val IWSPOptions = userPreferences.IWSPOptions

            // parse string to json array
            val gson = Gson()
            val jsonData = IWSPOptions ?: "[]"
            val json = gson.fromJson(jsonData, JsonArray::class.java).asJsonArray

            // map to List<Job> and update right list
            rightList = json.map {
                val company = it.asString
                Constant.getJobByCompanyName(company)
            }

            rightListAdapter.items = rightList
            rightListAdapter.notifyDataSetChanged()
        }

        val leftListAdapter = LeftListAdapter(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                runBlocking {
                    // read from data store
                    val userPreferences = userPreferencesRepository.IWSPOptionsFlow.first()
                    val IWSPOptions = userPreferences.IWSPOptions
                    val item = leftList[position]

                    // parse string to json array
                    val gson = Gson()
                    val jsonData = IWSPOptions ?: "[]"
                    val json = gson.fromJson(jsonData, JsonArray::class.java).asJsonArray
                    if (json.size() >= 3) {
                        return@runBlocking
                    }

                    // add to json if company doesn't exist
                    val companyExists = json.any { it.asString == item.company }
                    if (companyExists) {
                        return@runBlocking
                    }
                    json.add(item.company)

                    // convert to string and store to user preferences
                    val jsonStr = gson.toJson(json)
                    userPreferencesRepository.storeIWSPOptions(jsonStr)

                    // map to List<Job> and update right list
                    rightList = json.map {
                        val company = it.asString
                        Constant.getJobByCompanyName(company)
                    }

                    rightListAdapter.items = rightList
                    rightListAdapter.notifyItemInserted(rightList.size - 1)
                }
            }
        }, leftList)
        binding.recyclerviewleft.adapter = leftListAdapter
        binding.recyclerviewleft.layoutManager =
            LinearLayoutManager(this@MainActivity)

        binding.selectionClear.setOnClickListener {
            runBlocking {
                userPreferencesRepository.clear()
                rightList = listOf()
                rightListAdapter.items = rightList
                rightListAdapter.notifyDataSetChanged()
            }
        }

        binding.selectionSend.setOnClickListener {
            val text = binding.studentId.text.toString()
            // check if text is 7 characters long
            if (text.length != 7) {
                binding.errorMsg.text = "Invalid Id"
                return@setOnClickListener
            }

            // check if text is numerical only
            if (!TextUtils.isDigitsOnly(text)) {
                binding.errorMsg.text = "Invalid Id"
                return@setOnClickListener
            }

            // check if rightList is size 3
            if (rightList.size != 3) {
                binding.errorMsg.text = "Invalid Choices"
                return@setOnClickListener
            }
            binding.errorMsg.text = "OK"
        }

        binding.headerCompany.setOnClickListener {
            if (!isASC) {
                // sort left list ascending
                leftList = leftList.sortedBy { it.company }
                leftListAdapter.items = leftList
            } else {
                // sort left list descending
                leftList = leftList.sortedByDescending { it.company }
                leftListAdapter.items = leftList
            }
            leftListAdapter.notifyDataSetChanged()
            isASC = !isASC
        }
    }
}