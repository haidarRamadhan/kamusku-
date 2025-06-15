package com.example.kamusku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kamusku.databinding.FragmentAddBinding


class add : Fragment() {

    // declare binding variable
    lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        binding.fabAddId.setOnClickListener {
            // menampilkan fragment
        }

        // Ketika tombol ID->EN ditekan
        binding.fabAddId.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, FragmentAddInd())
                .addToBackStack(null)
                .commit()
        }


        return binding.root
    }

}

// event "fab_add_id"
