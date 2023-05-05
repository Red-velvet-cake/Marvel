package com.red_velvet.marvel.ui.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.red_velvet.marvel.BR
import com.red_velvet.marvel.databinding.ListComicsBinding

class ComicsAdapter(
    private var items: List<ComicsCollection>,
    private val comicsListener: ComicsInteractionListener
) : RecyclerView.Adapter<ComicsAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.apply {
            setVariable(BR.item, currentItem)
            setVariable(BR.listener, comicsListener)
        }
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<ComicsCollection>) {
        items = newItems
    }

    class ItemViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = ListComicsBinding.bind(binding.root)
    }

}