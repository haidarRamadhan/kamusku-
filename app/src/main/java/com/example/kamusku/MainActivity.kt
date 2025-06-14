package com.example.kamusku

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        openViewFragment()
        }

    // Note : Letakkan di luar on create
    fun openViewFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, add())
           .addToBackStack(null).commit()
    }

    }
