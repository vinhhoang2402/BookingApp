package com.example.bookingapp.ui.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseAdapter<M, V : BaseAdapter.BaseViewHolder<M>> :
    RecyclerView.Adapter<V>() {

    private val originItems: ArrayList<M> = ArrayList()
    protected val actualItems: ArrayList<M> = ArrayList()
    protected val compositeDisposable = CompositeDisposable()

    protected var TAG: String = "[OOBE][ADAPTER] ${javaClass.simpleName}"

    open fun updateItems(newItems: List<M>?, isDiffUtil: Boolean = false) {
        this@BaseAdapter.originItems.clear()
        this@BaseAdapter.originItems.addAll(newItems ?: emptyList())
        this@BaseAdapter.filterItems(isDiffUtil)
    }

    fun getItemWith(position: Int): M? {
        return this@BaseAdapter.actualItems.getOrNull(position)
    }

    private fun filterItems(isDiffUtil: Boolean) {
        val willDisplayingItems = this@BaseAdapter.originItems

        if (willDisplayingItems.isNotEmpty() && this@BaseAdapter.actualItems.isEmpty() || !isDiffUtil) {
            this@BaseAdapter.actualItems.clear()
            this@BaseAdapter.actualItems.addAll(willDisplayingItems)
            this@BaseAdapter.notifyDataSetChanged()
            return
        }

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = this@BaseAdapter.actualItems.size

            override fun getNewListSize(): Int = willDisplayingItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = this@BaseAdapter.actualItems[oldItemPosition]
                val newItem = willDisplayingItems[newItemPosition]
                return this@BaseAdapter.onCheckItem(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = this@BaseAdapter.actualItems[oldItemPosition]
                val newItem = willDisplayingItems[newItemPosition]
                return this@BaseAdapter.onCheckItemContents(oldItem, newItem)
            }

        }, true)

        this@BaseAdapter.actualItems.clear()
        this@BaseAdapter.actualItems.addAll(willDisplayingItems)
        diffResult.dispatchUpdatesTo(this@BaseAdapter)
    }

    open fun onCheckItemContents(oldItem: M, newItem: M): Boolean {
        return false
    }

    open fun onCheckItem(oldItem: M, newItem: M): Boolean {
        return false
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(this@BaseAdapter.actualItems[position], position)
    }

    override fun getItemCount(): Int {
        return this@BaseAdapter.actualItems.count()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        compositeDisposable.clear()
    }

    fun getItems(): List<M> {
        return actualItems
    }

    abstract class BaseViewHolder<M>(parent: ViewGroup, @LayoutRes layoutResId: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)) {

        open fun bind(item: M, position: Int) = Unit
    }
}