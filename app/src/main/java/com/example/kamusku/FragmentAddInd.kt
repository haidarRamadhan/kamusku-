package com.example.kamusku

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kamusku.config.Lite

class FragmentAddInd : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_ind, container, false)

        val indo = view.findViewById<EditText>(R.id.txt_npm)
        val eng = view.findViewById<EditText>(R.id.txt_nama)
        val btnSimpan = view.findViewById<Button>(R.id.btn_simpan)
        val btnBatal = view.findViewById<Button>(R.id.btn_batal)

        // Warna teks dan hint jadi hitam pekat
        indo.setTextColor(Color.BLACK)
        indo.setHintTextColor(Color.BLACK)

        eng.setTextColor(Color.BLACK)
        eng.setHintTextColor(Color.BLACK)

        btnSimpan.setTextColor(Color.BLACK)
        btnBatal.setTextColor(Color.BLACK)

        // Logika tombol Simpan
        btnSimpan.setOnClickListener {
            val db = Lite(requireContext()).writableDatabase
            val stmt = db.compileStatement("INSERT INTO tb_kalimat (indo, eng) VALUES (?, ?)")
            stmt.bindString(1, indo.text.toString())
            stmt.bindString(2, eng.text.toString())
            stmt.execute()

            Toast.makeText(requireContext(), "Berhasil disimpan", Toast.LENGTH_SHORT).show()
            indo.text.clear()
            eng.text.clear()
        }

        // Logika tombol Batal
        btnBatal.setOnClickListener {
            indo.text.clear()
            eng.text.clear()
            Toast.makeText(requireContext(), "Input dibersihkan", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
