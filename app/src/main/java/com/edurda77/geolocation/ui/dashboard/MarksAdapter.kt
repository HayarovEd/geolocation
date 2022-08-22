package com.edurda77.geolocation.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.geolocation.databinding.ItemListMarkBinding
import com.edurda77.geolocation.entity.MarkModel

class MarksAdapter(
    private val list: List<MarkModel>,
    private val onClickListener: OnStateClickListener,
    private val onDeleteListener: MarkClickDeleteInterface
) :
    RecyclerView.Adapter<MarksHolder>() {
    interface OnStateClickListener {
        fun onStateClick(markModel: MarkModel, position: Int)
    }

    interface MarkClickDeleteInterface {
        fun onDeleteIconClick(markModel: MarkModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarksHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarksHolder(ItemListMarkBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: MarksHolder, position: Int) {

        val markModel: MarkModel = list[position]
        holder.bind(markModel)

        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(markModel, position)
        }
        holder.onDeleteClick.setOnClickListener{
            onDeleteListener.onDeleteIconClick(markModel)
        }
    }

    override fun getItemCount(): Int = list.size
}