package com.example.kamusku

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kamusku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
// Layout inflater untuk merubah file XML agar bisa ditampilkan di layar
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.menu1.performClick()

        binding.menu1.setOnClickListener {
            openFragment1()
        }



        }

    // fungsi untuk menu_1
    // Note : Letakkan di luar on create
    fun openFragment1(){
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, id_en())
           .addToBackStack(null).commit()
    }
    }
