package com.example.keranscoffeeandspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.keranscoffeeandspace.dapur.DapurActivity
import com.example.keranscoffeeandspace.databinding.ActivityMainBinding
import com.example.keranscoffeeandspace.menu.MenuActivity
import com.example.keranscoffeeandspace.pesan.PesanActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Keran's Cafe and Space"

        binding.btnMenu.setOnClickListener(this)
        binding.btnPesan.setOnClickListener(this)
        binding.btnDapur.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_menu -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_pesan -> {
                val intent = Intent(this, PesanActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_dapur -> {
                val intent = Intent(this, DapurActivity::class.java)
                startActivity(intent)
            }
        }
    }
}