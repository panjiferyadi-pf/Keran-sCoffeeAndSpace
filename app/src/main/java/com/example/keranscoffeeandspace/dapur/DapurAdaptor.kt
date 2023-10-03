package com.example.keranscoffeeandspace.dapur

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keranscoffeeandspace.databinding.ItemListDapurBinding

class DapurAdaptor(val onItemClickCallback: IOnItemClickCallback) : RecyclerView.Adapter<DapurAdaptor.DapurViewHolder>() {

    var listDapur = ArrayList<PesanModel>()

    fun delete(index: Int) {
        this.listDapur.removeAt(index)
    }

    class DapurViewHolder(val binding: ItemListDapurBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DapurViewHolder {
        return DapurViewHolder(ItemListDapurBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DapurViewHolder, position: Int) {
        val data = listDapur[position]
        holder.binding.itemNama.text = data.nama
        holder.binding.itemNomorMeja.text = "No Meja\n${data.nomerMeja}"
        holder.binding.itemHarga.text = "Rp. ${data.harga}"
        holder.binding.itemWaktu.text = data.waktu

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data, position)
        }
    }

    override fun getItemCount(): Int {
        return listDapur.size
    }

    interface IOnItemClickCallback {
        fun onItemClicked(data: PesanModel, position: Int)
    }
}