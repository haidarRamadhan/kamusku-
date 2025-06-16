package com.example.kamusku

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kamusku.config.Lite
import com.google.android.material.snackbar.Snackbar

class FragmentViewIdEn : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_id_en, container, false)
        val listContainer = view.findViewById<LinearLayout>(R.id.list_container)

        val db = Lite(requireContext()).readableDatabase
        val cursor = db.rawQuery("SELECT indo, eng FROM tb_kalimat", null)

        if (cursor.moveToFirst()) {
            do {
                val indo = cursor.getString(0)
                val eng = cursor.getString(1)

                val itemLayout = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.HORIZONTAL
                    setPadding(8, 8, 8, 8)

                    val tvIndo = TextView(requireContext()).apply {
                        text = indo
                        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                        textSize = 18f
                    }

                    val tvEng = TextView(requireContext()).apply {
                        text = eng
                        layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                        textSize = 18f
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    }

                    val btnDelete = ImageButton(requireContext()).apply {
                        setImageResource(android.R.drawable.ic_menu_delete)
                        setBackgroundColor(Color.TRANSPARENT)
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                    }

                    btnDelete.setOnClickListener {
                        val dbDel = Lite(requireContext()).writableDatabase
                        dbDel.execSQL("DELETE FROM tb_kalimat WHERE indo = ? AND eng = ?", arrayOf(indo, eng))
                        dbDel.close()
                        listContainer.removeView(this)
                        Snackbar.make(requireView(), "Data dihapus", Snackbar.LENGTH_SHORT).show()
                    }

                    addView(tvIndo)
                    addView(tvEng)
                    addView(btnDelete)
                }

                listContainer.addView(itemLayout)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return view
    }
}
