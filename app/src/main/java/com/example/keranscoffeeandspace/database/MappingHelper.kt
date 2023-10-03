package com.example.keranscoffeeandspace.database

import android.database.Cursor
import com.example.keranscoffeeandspace.model.PesanModel

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<PesanModel> {
        val notesList = ArrayList<PesanModel>()
        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumn._ID))
                val nomerMeja = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumn.NOMER_MEJA))
                val nama = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumn.NAMA))
                val harga = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumn.HARGA))
                val waktu = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumn.WAKTU))
                notesList.add(PesanModel(id, nomerMeja, nama, harga, waktu))
            }
        }
        return notesList
    }
}