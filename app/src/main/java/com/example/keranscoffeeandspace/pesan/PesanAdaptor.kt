package com.example.keranscoffeeandspace.pesan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keranscoffeeandspace.dapur.DapurAdaptor
import com.example.keranscoffeeandspace.databinding.ItemListPesanBinding
import com.example.keranscoffeeandspace.model.PesanModel

class PesanAdaptor(private val onItemClickCallback: DapurAdaptor.IOnItemClickCallback) : RecyclerView.Adapter<PesanAdaptor.ListViewHolder>() {

    var listPesan = ArrayList<PesanModel>()

    fun delete(index: Int) {
        this.listPesan.removeAt(index)
    }

    class ListViewHolder(val binding: ItemListPesanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemListPesanBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listPesan[position]
        holder.binding.itemPesanan.text = data.nama
        holder.binding.itemHarga.text = "Rp. ${data.harga}"

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data, position)
        }
    }

    verride fun getItemCount(): Int {
        return listPesan.size
    }

    interface IOnItemClickCallback {
        fun onItemClicked(data: PesanModel, position: Int)
    }

}