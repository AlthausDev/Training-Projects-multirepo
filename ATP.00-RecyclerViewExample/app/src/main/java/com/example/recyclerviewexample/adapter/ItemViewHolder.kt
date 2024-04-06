package com.example.recyclerviewexample.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.Item
import com.example.recyclerviewexample.databinding.ItemItemBinding

class ItemViewHolder (view : View) : RecyclerView.ViewHolder (view){

    val binding = ItemItemBinding.bind(view)

    //val valor1 = view.findViewById<TextView>(R.id.item1)
    //val valor2 = view.findViewById<TextView>(R.id.item2)


    fun render(itemModel : Item) {
        binding.item1.text = itemModel.valor1
        binding.item2.text = itemModel.valor2

        binding.item1.setOnClickListener {
            Toast.makeText(
                binding.item1.context,
                itemModel.valor1,
                Toast.LENGTH_SHORT
            ).show()
        }

    itemView.setOnClickListener {
            Toast.makeText(
            binding.item1.context,
            itemModel.valor1,
            Toast.LENGTH_SHORT
        ).show()
    }


//        valor1.text = itemModel.valor1
//        valor2.text = itemModel.valor2
    }
}