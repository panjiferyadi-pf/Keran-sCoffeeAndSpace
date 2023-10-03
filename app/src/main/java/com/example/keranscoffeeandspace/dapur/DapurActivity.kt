package com.example.keranscoffeeandspace.dapur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keranscoffeeandspace.R
import com.example.keranscoffeeandspace.databinding.ActivityDapurBinding
import com.example.keranscoffeeandspace.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers

class DapurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDapurBinding
    private lateinit var pesanHelper: PesanHelper
    private lateinit var adaptor: DapurAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Keran's Coffee and Space"

        adaptor = DapurAdaptor(object : DapurAdaptor.IOnItemClickCallback {
            override fun onItemClicked(data: PesanModel, position: Int) {
                showDialog(data, position)
            }
        })
        showListDapur()
    }

    fun showListDapur() {
        lifecycleScope.launch {
            pesanHelper = PesanHelper.getInstance(applicationContext)
            pesanHelper.open()

            val defferedPesan = async(Dispatchers.IO) {
                val cursor = pesanHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }

            val dataDapur = defferedPesan.await()
            if(dataDapur.size > 0) {
                adaptor.listDapur = dataDapur
            }else{
                adaptor.listDapur = arrayListOf()
            }
            pesanHelper.close()
            showRecycleviewDapur()
        }
    }

    fun showRecycleviewDapur(){
        binding.recycleviewDapur.apply {
            adapter = adaptor
            layoutManager = LinearLayoutManager(this@DapurActivity)
        }
    }

    fun showDialog(data: PesanModel, position: Int) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Konfirmasi").setMessage("Apakah Menu Sudah Siap?")
            .setPositiveButton("Sudah") { _, _ ->
                pesanHelper.open()
                pesanHelper.deletebyId(data.id.toString())
                adaptor.delete(position)
                showRecycleviewDapur()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.cancel()
            }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
















