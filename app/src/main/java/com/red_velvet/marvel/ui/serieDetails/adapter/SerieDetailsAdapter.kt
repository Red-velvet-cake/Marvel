package com.red_velvet.marvel.ui.serieDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.red_velvet.marvel.BR
import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.databinding.ItemCreatorBinding
import com.red_velvet.marvel.databinding.ItemTopSerieDetailsBinding
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class SerieDetailsAdapter(
    private var detailsItem: List<DetailsItem<Any>>,
    private val listener: CreatorListenerInteraction,
) : BaseAdapter<DetailsItem<Any>>(detailsItem, listener) {

    override val layoutId: Int = R.layout.item_top_serie_details

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_INFO -> {
                ItemInfoViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        layoutId,
                        parent,
                        false
                    )
                )

            }

            else -> {
                ItemCreatorsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_creator,
                        parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ItemInfoViewHolder -> bindInfo(holder, position)
            is ItemCreatorsViewHolder -> bindCreator(holder, position)
        }
    }

    private fun bindInfo(holder: ItemInfoViewHolder, position: Int) {
        val currentItem = detailsItem[position].item as SeriesResponse
        holder.binding.setVariable(BR.item, currentItem)
    }

    private fun bindCreator(holder: ItemCreatorsViewHolder, position: Int) {
        val currentItem = detailsItem[position].item as CreatorsResponse
        holder.binding.setVariable(BR.item, currentItem)
        holder.binding.setVariable(BR.listener, listener)
    }


    override fun getItemViewType(position: Int): Int {
        return when (detailsItem[position].type) {
            DetailsItemType.INFO -> VIEW_INFO
            DetailsItemType.CREATORS -> Creators
        }
    }

    class ItemCreatorsViewHolder(val binding: ItemCreatorBinding) : BaseViewHolder(binding)
    class ItemInfoViewHolder(val binding: ItemTopSerieDetailsBinding) : BaseViewHolder(binding)

    companion object {
        const val VIEW_INFO = 0
        const val Creators = 1
    }

    interface CreatorListenerInteraction : BaseInteractionListener {
        fun onClickCreator()
    }
}