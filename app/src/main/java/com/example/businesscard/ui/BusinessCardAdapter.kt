package com.example.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.data.BusinessCard
import com.example.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}
    var deleteListener: (BusinessCard) -> Unit = {}

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            binding.tvNome.text = item.nome
            binding.tvTelefone.text = item.telefone
            binding.tvEmail.text = item.email
            binding.tvEmpresa.text = item.empresa
            binding.mcvContent.setCardBackgroundColor(Color.parseColor(item.background))

             /*
             Change the text color to white if the background color is black or a color tone
             Color.parseColor return an int number witch is used in the comparison
             if between -16777216 and -6908266 is been considered a dark color tone
             */
            if (Color.parseColor(item.background) >= Color.parseColor("black") &&
                Color.parseColor(item.background) <= Color.parseColor("#969696")) { //#62676c

                binding.ivDelete.setColorFilter(Color.parseColor("#FFFFFF"))
                binding.tvNome.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvTelefone.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvEmail.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvEmpresa.setTextColor(Color.parseColor("#FFFFFF"))
            }

            binding.mcvContent.setOnClickListener {
                listenerShare(it)
            }

            binding.ivDelete.setOnClickListener {
                deleteListener(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id
}
