package com.hihasan.notes.utils.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<S>(
    private val mContext: Context?,
    private var list: List<S>?,//generic list can take any Object
    private val layoutId: Int,// the item_layout
    private val listener: BaseAdapterListener? =null// call back inside your view
) : RecyclerView.Adapter<BaseAdapter.DataBindingViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        return DataBindingViewHolder(DataBindingUtil.inflate(layoutInflater, layoutId, parent, false))
    }



    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun getLayoutId(): Int {
        return layoutId
    }



    fun deleteItem(position: Int,holder: DataBindingViewHolder) {
        /*val mutableList = list?.toMutableList()
        mutableList!!.removeAt(position)
        setItem(mutableList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list?.size!!)
        holder.itemView.visibility = View.GONE*/


        val arrayList = list as ArrayList<S>

        arrayList.removeAt(position)
        setItem(arrayList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, arrayList.size)
        //   holder.itemView.visibility = View.GONE
    }

    fun setItem(newList: List<S>){
/*        if (list.isNullOrEmpty()){
            list = newList
            notifyDataSetChanged()
        }
        list = newList
        notifyDataSetChanged()*/

        val itemListDiffUtil = ItemListDiffUtil(list!!, newList)
        val diffUtilResult = DiffUtil.calculateDiff(itemListDiffUtil)
        list = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun getItem(position: Int):S {
        list?.let {
            if (position < it.size)
                return it[position]
        }
        return list?.get(position) ?: null as S
    }

    fun getItemList(): List<S>? {
        return list
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, @SuppressLint("RecyclerView") position: Int) {
        listener?.onBind(holder, position, list?.get(position), layoutId)

    }

    interface BaseAdapterListener {
        fun onBind(holder: DataBindingViewHolder, position: Int, item: Any?, layoutId: Int)
    }

    class DataBindingViewHolder( val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}