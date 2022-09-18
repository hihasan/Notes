package com.hihasan.notes.utils.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BasePagingAdapter<S : Any>(
    private val mContext: Context?,
    private val layoutId: Int,// the item_layout
    private val listener: PagingAdapterListener? = null// call back inside your view
) : PagingDataAdapter<S, BasePagingAdapter.DataBindingViewHolder>(DiffUtilCallBack()) {

    class DiffUtilCallBack<S> : DiffUtil.ItemCallback<S>() {
        override fun areItemsTheSame(oldItem: S, newItem: S): Boolean {
            return oldItem === newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: S, newItem: S): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return DataBindingViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                layoutId,
                parent,
                false
            )
        )
    }

/*    override fun getItemCount(): Int {
        return list?.size ?: 0
    }*/

    fun getLayoutId(): Int {
        return layoutId
    }

    /*  fun getItemList(): List<S>? {
          return list
      }*/

    /* fun deleteItem(position: Int,holder: DataBindingViewHolder) {
         *//*val mutableList = list?.toMutableList()
        mutableList!!.removeAt(position)
        setItem(mutableList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list?.size!!)
        holder.itemView.visibility = View.GONE*//*
        val arrayList = list as ArrayList<S>
        arrayList.removeAt(position)
        setItem(arrayList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list?.size!!)
        holder.itemView.visibility = View.GONE
    }
    fun setItem(newList: List<S>){
*//*        if (list.isNullOrEmpty()){
            list = newList
            notifyDataSetChanged()
        }
        list = newList
        notifyDataSetChanged()*//*
        val itemListDiffUtil = ItemListDiffUtil(list!!, newList)
        val diffUtilResult = DiffUtil.calculateDiff(itemListDiffUtil)
        list = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }*/

    fun getItemByPosition(position: Int) = getItem(position)

    /* suspend fun removeAt(position: Int) {
        val snap =  snapshot().items.toMutableList().apply {
             removeAt(position)
         }
         submitData(PagingData.from(snap))
         refresh()
     }*/

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        listener?.onBind(holder, position, getItem(position), layoutId)

    }

    interface PagingAdapterListener {
        fun onBind(holder: DataBindingViewHolder, position: Int, item: Any?, layoutId: Int)
    }

    class DataBindingViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}