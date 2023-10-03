package com.example.keranscoffeeandspace.pesan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.keranscoffeeandspace.R
import com.example.keranscoffeeandspace.databinding.ActivityPesanBinding
import com.example.keranscoffeeandspace.keys.Keys

class PesanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Keran Coffee and Space"

        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_next -> {
                val nomorMeja = binding.inputNomermeja.text.toString()
                if (nomorMeja.isNotEmpty()) {
                    val intent = Intent(this, ListPesanActivity::class.java)
                    intent.putExtra(Keys.KEY_NO_MEJA, nomorMeja)
                    startActivity(intent)
                } else {
                    binding.inputNomermeja.error = "Tidak boleh kosong"
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}