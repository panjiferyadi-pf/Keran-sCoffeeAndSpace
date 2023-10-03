package com.example.keranscoffeeandspace.menu

import android.content.ContentValues
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
//import com.bumptech.glide.Glide
import androidx.appcompat.app.AlertDialog
import com.example.keranscoffeeandspace.database.DatabaseContract
import com.example.keranscoffeeandspace.database.PesanHelper
import com.example.keranscoffeeandspace.databinding.ActivityDetailListMenuBinding
import com.example.keranscoffeeandspace.keys.Keys
import com.example.keranscoffeeandspace.model.ItemMenu
import com.example.keranscoffeeandspace.pesan.ListPesanActivity
import com.example.keranscoffeeandspace.pesan.PesanActivity
import java.util.Date
import java.util.Locale

class DetailListMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailListMenuBinding
    private var nomorMeja: String? = null
    private lateinit var pesanHelper: PesanHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailListMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pesanHelper = PesanHelper.getInstance(applicationContext)
        pesanHelper.open()

        val getData = intent.getParcelableExtra<ItemMenu>(Keys.KEY_DETAIL_MENU)!!
        val getMenu = intent.getStringExtra(Keys.KEY_MENU)!!
        nomorMeja = intent.getStringExtra(Keys.KEY_NO_MEJA)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Keran' Coffee and Space"

        binding.idtvKategori.text = getMenu
        binding.idtvNama.text = getData.nama
        binding.idtvDeskripsi.text = getData.deskripsi
        binding.idtvHarga.text = "HARGA : ${getData.harga}"
        Glide.with(this)
            .load(getData.image)
            .fitCenter()
            .into(binding.idimgKategori)

        binding.idbtnPesan.setOnClickListener {
            showDialog(getData, nomorMeja)
        }
    }

    fun showDialog(data: ItemMenu, noMeja: String?) {
        val title = if (noMeja != null) "Yakin ingin memesan ini?" else "Anda belum memilih No Meja makanan"
        val messege = if (noMeja != null) "No meja : $noMeja \nMenu : ${data.nama} \nHarga : ${data.harga}" else "Pilih No Meja dulu ?"

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
            .setMessage(messege)
            .setPositiveButton("Ya") {_, _ ->
                if (noMeja != null) {
                    val values = ContentValues()
                    values.put(DatabaseContract.NoteColumn.NOMER_MEJA, noMeja)
                    values.put(DatabaseContract.NoteColumn.NAMA, data.nama)
                    values.put(DatabaseContract.NoteColumn.HARGA, data.harga)
                    values.put(DatabaseContract.NoteColumn.WAKTU, getCurrentDate())
                    val result = pesanHelper.insert(values)
                    if (result > 0) {
                        val intent = Intent(this, ListPesanActivity::class.java)
                        intent.putExtra(Keys.KEY_NO_MEJA, noMeja)
                        startActivity(intent)
                    }
                } else {
                    val intent = Intent(this, PesanActivity::class.java)
                    intent.putExtra(Keys.KEY_MAIN, "Pilih No Meja")
                    startActivity(intent)
                }
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}