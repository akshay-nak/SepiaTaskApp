package com.learning.sepiataskapp.ui.petlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.sepiataskapp.R
import com.learning.sepiataskapp.data.model.Pet
import kotlinx.android.synthetic.main.item_pet_list.view.*

class PetListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataList = ArrayList<Pet>()
    private var onPetListItemClickListener: PetListItemClickListener? = null
    fun setData(petList: ArrayList<Pet>) {
        dataList.clear()
        dataList.addAll(petList)
        notifyItemChanged(0, dataList.size)
    }

    fun setPetListItemClickListener(onPetListItemClickListener: PetListItemClickListener){
        this.onPetListItemClickListener = onPetListItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PetListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pet_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pet = dataList[position]
        holder.itemView.tv_title.text = pet.title
        Glide.with(holder.itemView.context)
            .load(pet.imageURL)
            .into(holder.itemView.iv_pet_image)
        holder.itemView.setOnClickListener {
            onPetListItemClickListener?.onPetItemClicked(pet)
        }
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    private class PetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //Added a interface to work as a listener to handle the recycler item click
    interface PetListItemClickListener {
        fun onPetItemClicked(pet: Pet)
    }
}