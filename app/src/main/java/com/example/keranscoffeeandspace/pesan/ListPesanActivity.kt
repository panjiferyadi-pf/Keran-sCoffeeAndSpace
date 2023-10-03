package com.example.keranscoffeeandspace.pesan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keranscoffeeandspace.MainActivity
import com.example.keranscoffeeandspace.R
import com.example.keranscoffeeandspace.databinding.ActivityListPesanBinding
import com.example.keranscoffeeandspace.keys.Keys
import com.example.keranscoffeeandspace.menu.MenuActivity
import com.example.keranscoffeeandspace.model.PesanModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async

class ListPesanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPesanBinding
    private lateinit var adaptor: PesanAdaptor
    private lateinit var pesanHelper: PesanHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPesanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val nomorMeja = intent.getStringExtra(Keys.KEY_NO_MEJA)!!

        binding.idbtnKirim.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.idbtnTambah.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra(Keys.KEY_NO_MEJA, nomorMeja)
            startActivity(intent)
        }

        adaptor = PesanAdaptor(object : PesanAdaptor.IOnItemClickCallback {
            override fun onItemClicked(data: PesanModel, position: Int) {
                val dialog = AlertDialog.Builder(this@ListPesanActivity)
                dialog.setTitle("Hapus")
                    .setMessage("Apakah anda yakin menghapus menu ini ?")
                    .setPositiveButton("YA") { _, _ ->
                        pesanHelper.open()
                        pesanHelper.deleteById(data.id.toString())
                        adaptor.delete(position)
                        showListPesan()
                    }
                    .setNegativeButton("BATAL") { dialog, _ ->
                        dialog.cancel()
                    }
                    .create()
                    .show()
            }
        })
        showPesan(nomorMeja)
    }

    fun showPesan(noMeja: String) {
        lifecycleScope.launch {
            pesanHelper = PesanHelper.getInstance(applicationContext)
            pesanHelper.open()

            val defferedPesanan = async(Dispatchers.IO) {
                val cursor = pesanHelper.queryByNoMeja(noMeja)
                MappingHelper.mapCursorToArrayList(cursor)
            }

            val pesanan = defferedPesanan.await()
            if (pesanan.size > 0) {
                adaptor.listPesan = pesanan
            } else {
                adaptor.listPesan = arrayListOf()
            }
            pesanHelper.close()

            showListPesan()
            supportActionBar?.title = "Pesanan No Meja $noMeja"
        }
    }

    fun showListPesan() {
        binding.recycleviewListpesan.apply {
            adapter = adaptor
            layoutManager = LinearLayoutManager(this@ListPesanActivity)
        }
        var total = 0
        for (item in adaptor.listPesan) {
            total += item.harga.toInt()
        }
        binding.idtvTotalharga.text = "Total harga $total"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}