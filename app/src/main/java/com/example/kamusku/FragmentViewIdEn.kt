package com.example.kamusku

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.kamusku.config.Lite
import com.google.android.material.snackbar.Snackbar

class FragmentViewIdEn : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menghubungkan layout XML ke kode
        val view = inflater.inflate(R.layout.fragment_view_id_en, container, false)
        val listContainer = view.findViewById<LinearLayout>(R.id.list_container)

        // Buka database untuk membaca data
        val db = Lite(requireContext()).readableDatabase
        val cursor = db.rawQuery("SELECT indo, eng FROM tb_kalimat", null)

        // Cek apakah ada data di cursor
        if (cursor.moveToFirst()) {
            do {
                // Ambil nilai bahasa Indonesia dan Inggris
                val indo = cursor.getString(0)
                val eng = cursor.getString(1)

                // Buat satu baris layout secara dinamis
                val itemLayout = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.HORIZONTAL
                    setPadding(8, 8, 8, 8)

                    // Teks Bahasa Indonesia
                    val tvIndo = TextView(requireContext()).apply {
                        text = indo
                        textSize = 18f
                        setTextColor(Color.BLACK) // warna hitam pekat
                        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                    }

                    // Teks Bahasa Inggris
                    val tvEng = TextView(requireContext()).apply {
                        text = eng
                        textSize = 18f
                        setTextColor(Color.BLACK) // warna hitam pekat
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                    }

                    // Tombol Edit
                    val btnEdit = ImageButton(requireContext()).apply {
                        setImageResource(android.R.drawable.ic_menu_edit)
                        setBackgroundColor(Color.TRANSPARENT)
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }

                    // Tombol Hapus
                    val btnDelete = ImageButton(requireContext()).apply {
                        setImageResource(android.R.drawable.ic_menu_delete)
                        setBackgroundColor(Color.TRANSPARENT)
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }

                    // Aksi saat tombol hapus ditekan
                    btnDelete.setOnClickListener {
                        val dbDel = Lite(requireContext()).writableDatabase
                        dbDel.execSQL("DELETE FROM tb_kalimat WHERE indo = ? AND eng = ?", arrayOf(indo, eng))
                        dbDel.close()
                        listContainer.removeView(this) // hapus tampilan
                        Snackbar.make(requireView(), "Data dihapus", Snackbar.LENGTH_SHORT).show()
                    }

                    // Aksi saat tombol edit ditekan
                    btnEdit.setOnClickListener {
                        showEditDialog(tvIndo, tvEng, listContainer, this)
                    }

                    // Tambahkan elemen ke layout baris
                    addView(tvIndo)
                    addView(tvEng)
                    addView(btnEdit)
                    addView(btnDelete)
                }

                // Tambahkan baris layout ke tampilan utama
                listContainer.addView(itemLayout)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return view
    }

    // Fungsi untuk menampilkan dialog edit data
    private fun showEditDialog(tvIndo: TextView, tvEng: TextView, container: LinearLayout, itemLayout: LinearLayout) {
        // Gunakan layout custom dialog_edit.xml
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit, null)
        val inputIndo = dialogView.findViewById<EditText>(R.id.edit_indo)
        val inputEng = dialogView.findViewById<EditText>(R.id.edit_eng)

        // Isi data awal ke dalam EditText
        inputIndo.setText(tvIndo.text.toString())
        inputEng.setText(tvEng.text.toString())

        // Tampilkan dialog
        AlertDialog.Builder(requireContext())
            .setTitle("Edit Data")
            .setView(dialogView)
            .setPositiveButton("Simpan") { _, _ ->
                val newIndo = inputIndo.text.toString()
                val newEng = inputEng.text.toString()

                // Update ke database
                val db = Lite(requireContext()).writableDatabase
                db.execSQL(
                    "UPDATE tb_kalimat SET indo = ?, eng = ? WHERE indo = ? AND eng = ?",
                    arrayOf(newIndo, newEng, tvIndo.text.toString(), tvEng.text.toString())
                )
                db.close()

                // Update tampilan teks langsung
                tvIndo.text = newIndo
                tvEng.text = newEng
                Snackbar.make(requireView(), "Data diperbarui", Snackbar.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
