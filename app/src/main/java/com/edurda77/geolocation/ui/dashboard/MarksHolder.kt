package com.edurda77.geolocation.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.edurda77.geolocation.databinding.ItemListMarkBinding
import com.edurda77.geolocation.entity.MarkModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MarksHolder(private val binding: ItemListMarkBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val onDeleteClick: FloatingActionButton = binding.deleteMark

    fun bind(markModel: MarkModel) {
        binding.titleItem.text = markModel.titleMark
        binding.annotationMark.text = markModel.annotationMark
        val latitudeItem = "Широта - ${markModel.latitudeMark}"
        val longitudeItem = "Долгота - ${markModel.longitudeMark}"
        binding.latitudeMark.text = latitudeItem
        binding.longitudeMark.text = longitudeItem
    }

}