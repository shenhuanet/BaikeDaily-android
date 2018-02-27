package com.shenhua.baikedaily.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.*

/**
 * RecyclerView通用适配器
 * Created by Shenhua on 8/21/2016.
 */
abstract class BaseRecyclerAdapter<T>(protected var mContext: Context, datas: ArrayList<T>?)
    : RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerViewHolder>() {
    protected var mDatas: ArrayList<T>? = null
    protected var mInflater: LayoutInflater
    protected var mOnItemClickListener: OnItemClickListener<T>? = null
    protected var mOnItemLongClickListener: OnItemLongClickListener<T>? = null

    var datas: ArrayList<T>?
        get() = mDatas
        set(datas) {
            mDatas = datas
            notifyDataSetChanged()
        }

    init {
        this.mDatas = datas ?: ArrayList()
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder {
        val holder = BaseRecyclerViewHolder(mContext, mInflater.inflate(getItemViewId(viewType), parent, false), viewType)
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener { v -> mOnItemClickListener!!.onItemClick(v, holder.layoutPosition, mDatas!![holder.adapterPosition]) }
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener { view ->
                mOnItemLongClickListener!!.onItemLongClick(view, holder.layoutPosition, mDatas!![holder.adapterPosition])
                true
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        bindData(holder, position, mDatas!![position])
    }

    override fun getItemCount(): Int {
        return if (mDatas != null) mDatas!!.size else 0
    }

    fun addItem(position: Int, itemData: T) {
        mDatas!!.add(position, itemData)
        notifyItemInserted(position)
    }

    fun deleteItem(position: Int) {
        mDatas!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addMoreItem(datas: List<T>) {
        val startPosition = mDatas!!.size
        mDatas!!.addAll(datas)
        notifyItemRangeChanged(startPosition, mDatas!!.size)
    }

    abstract fun getItemViewId(viewType: Int): Int

    abstract fun bindData(holder: BaseRecyclerViewHolder, position: Int, item: T)

    fun setOnItemClickListener(clickListener: OnItemClickListener<T>) {
        this.mOnItemClickListener = clickListener
    }

    fun setOnItemLongClickListener(longClickListener: OnItemLongClickListener<T>) {
        this.mOnItemLongClickListener = longClickListener
    }

    interface OnItemClickListener<T> {
        fun onItemClick(view: View, position: Int, data: T)
    }

    interface OnItemLongClickListener<T> {
        fun onItemLongClick(view: View, position: Int, data: T): Boolean
    }

    class BaseRecyclerViewHolder(private val mContext: Context, itemView: View, val viewType: Int) : RecyclerView.ViewHolder(itemView) {

        private val mViews: SparseArray<View> = SparseArray()

        private fun <T : View> findViewById(viewId: Int): T {
            var view: View? = mViews.get(viewId)
            if (view == null) {
                view = itemView.findViewById(viewId)
                mViews.put(viewId, view)
            }
            return view as T
        }

        fun getView(viewId: Int): View {
            return findViewById(viewId)
        }

        fun setText(viewId: Int, value: String): BaseRecyclerViewHolder {
            val view = findViewById<TextView>(viewId)
            view.text = value
            return this
        }

        fun setBackground(viewId: Int, resId: Int): BaseRecyclerViewHolder {
            val view = findViewById<View>(viewId)
            view.setBackgroundResource(resId)
            return this
        }

        fun setBackgroundColor(viewId: Int, color: Int): BaseRecyclerViewHolder {
            val view = findViewById<View>(viewId)
            view.setBackgroundColor(color)
            return this
        }

        fun setImage(viewId: Int, resId: Int): BaseRecyclerViewHolder {
            val view = findViewById<ImageView>(viewId)
            //            Glide.with(mContext).load(resId).centerCrop().into(view);
            return this
        }

        fun setImage(viewId: Int, url: String): BaseRecyclerViewHolder {
            val view = findViewById<ImageView>(viewId)
            //            Glide.with(mContext).load(url).centerCrop().into(view);
            return this
        }

        fun setImage(viewId: Int, drawable: Drawable): BaseRecyclerViewHolder {
            val view = findViewById<ImageView>(viewId)
            //            Glide.with(mContext).load(drawable).centerCrop().into(view);
            return this
        }

        fun setOnItemViewClickListener(viewId: Int, listener: View.OnClickListener): BaseRecyclerViewHolder {
            val view = findViewById<View>(viewId)
            view.setOnClickListener(listener)
            return this
        }

    }

}
