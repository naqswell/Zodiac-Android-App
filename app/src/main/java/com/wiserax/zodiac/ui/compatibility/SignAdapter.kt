package com.wiserax.zodiac.ui.compatibility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wiserax.zodiac.databinding.InstanceSignBinding

class SignAdapter: RecyclerView.Adapter<SignAdapter.SignHolder>() {

    private val signs = Sign.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignHolder {
        val itemBinding = InstanceSignBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        parent.overScrollMode = View.OVER_SCROLL_NEVER

        return SignHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SignHolder, position: Int) {
        val sign = signs[position]

        holder.bind(sign)
    }

    override fun getItemCount(): Int {
        return signs.size
    }

    fun getCurrentTitle(position: Int) = signs[position].toString()

    class SignHolder(private val binding: InstanceSignBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(sign: Sign) {
            binding.textViewSignName.text = sign.title
            binding.textViewSignDate.text = sign.dates
            binding.imageViewSign.setImageResource(sign.image)
        }
    }
}