package com.example.keranscoffeeandspace.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.keranscoffeeandspace.database.DatabaseContract.NoteColumn.Companion.TABLE_NAME
import com.example.keranscoffeeandspace.database.DatabaseContract.NoteColumn.Companion.WAKTU

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbpesanan"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                "(${DatabaseContract.NoteColumn._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DatabaseContract.NoteColumn.NOMER_MEJA} VARCHAR(2)," +
                "${DatabaseContract.NoteColumn.NAMA} VARCHAR(50)," +
                "${DatabaseContract.NoteColumn.HARGA} VARCHAR(10)," +
                "${WAKTU} VARCHAR(20));"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}