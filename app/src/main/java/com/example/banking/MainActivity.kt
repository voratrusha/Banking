package com.example.banking

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banking.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
private val TAG = "MainActivity"
    companion object{
        lateinit var context : Context
        lateinit var database:Database
        lateinit var list:ArrayList<UserModel>
        lateinit var adapter:UserAdapter
        fun Updated() {
             list.clear()
            list= database.showData()
            adapter.update(list)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Database(applicationContext)

        list = database.showData()
        adapter = UserAdapter(list)
        binding.recycleview.layoutManager = LinearLayoutManager(applicationContext)
        binding.recycleview.adapter = adapter

        var date = Calendar.getInstance()
        var formate = SimpleDateFormat("d/M/y h:m:s")
        var current = formate.format(date.time).toString()
        Log.e(TAG, "onCreate: Current Date ==== $current" )



            Updated()
        }
    }
}