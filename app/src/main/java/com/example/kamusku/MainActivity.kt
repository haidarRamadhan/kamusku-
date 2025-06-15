package com.example.kamusku

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.kamusku.databinding.ActivityMainBinding
import com.example.kamusku.add
import com.example.kamusku.FragmentAddInd

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default fragment ditampilkan pertama kali
        openViewFragment()


    }

    fun openViewFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrame, add()) // default fragment pertama
            .commit()
    }
}
