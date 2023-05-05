package com.red_velvet.marvel.ui.comics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.ListComicsBinding

class ComicsScreenAdapter(
    private var items: List<ComicsCollection>,
    private val comicsListener: ComicsInteractionListener
) : RecyclerView.Adapter<ComicsScreenAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_comics,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        holder.binding.apply {
            textViewTitle.text = currentItem.title
            recyclerComics.adapter = ComicAdapter(
                currentItem.comics.toData() ?: emptyList(),
                comicsListener
            )
        }
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<ComicsCollection>) {
        items = newItems
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListComicsBinding.bind(itemView)
    }

}