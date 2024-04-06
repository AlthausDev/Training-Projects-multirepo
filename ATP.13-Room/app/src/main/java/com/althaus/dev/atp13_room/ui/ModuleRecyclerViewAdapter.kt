package com.althaus.dev.atp13_room.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.althaus.dev.atp13_room.data.database.Module
import com.althaus.dev.atp13_room.databinding.FragmentItemBinding

class ModuleRecyclerViewAdapter
    : ListAdapter<Module, ModuleRecyclerViewAdapter.ModuleViewHolder>(DiffUtilItemCallback) {

    companion object {
        val DiffUtilItemCallback = object : DiffUtil.ItemCallback<Module>() {
            override fun areItemsTheSame(oldItem: Module, newItem: Module): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Module, newItem: Module): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        return ModuleViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ModuleViewHolder(private var binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(module: Module) {
            binding.moduleId.text = module.id.toString()
            binding.moduleCredits.text = module.credits.toString()
            binding.moduleName.text = module.name
        }
    }
}
