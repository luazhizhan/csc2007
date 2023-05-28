package edu.singaporetech.grocerylist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.singaporetech.grocerylist.databinding.ActivityMainBinding

/**
 * Lab 03: Displaying Lists and Activity Lifecycle
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var groceryList = Constants.getGroceries()
    private lateinit var groceryAdapter: GroceryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate()")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groceryAdapter = GroceryAdapter(groceryList)
        binding.recyclerViewGroceryList.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerViewGroceryList.adapter = groceryAdapter
        binding.recyclerViewGroceryList.setHasFixedSize(true)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(
            "grocery_list",
            groceryList
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        groceryList = savedInstanceState.getParcelableArrayList("grocery_list")!!
        groceryAdapter.updateList(groceryList)
    }
}