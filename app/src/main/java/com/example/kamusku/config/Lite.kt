package com.example.kamusku.config

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kamusku.config.Constant.Companion.DB_NAME
import com.example.kamusku.config.Constant.Companion.DB_VERSION


class Lite(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE tb_kalimat (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                indo TEXT,
                eng TEXT
            );
        """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS tb_kalimat")
        onCreate(db)
    }
}
