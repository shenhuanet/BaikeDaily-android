package com.shenhua.baikedaily.ui.adapter

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.shenhua.baikedaily.R
import com.shenhua.baikedaily.bean.Baike
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by shenhua on 2017-12-13-0013.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class CardAdapter(mContext: Context, datas: ArrayList<Baike>?) : BaseRecyclerAdapter<Baike>(mContext, datas) {

    val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.img_card)
            .error(R.drawable.img_card)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)!!
    private val sm = SimpleDateFormat("yyyy.MM.dd", Locale.CHINA)

    override fun getItemViewId(viewType: Int): Int {
        return R.layout.item_content
    }

    override fun bindData(holder: BaseRecyclerViewHolder, position: Int, item: Baike) {
        holder.setText(R.id.tvTitle, item.title!!)
        holder.setText(R.id.tvDesc, item.desc!!)
        val iv = holder.getView(R.id.image) as ImageView
        Glide.with(mContext).load(item.pic!!).apply(options).into(iv)
        val text = item.author!! + " |\n" + sm.format(Date(item.publishTime * 1000))
        val spanString = SpannableString(text)
        spanString.setSpan(TextAppearanceSpan(mContext, R.style.TimeTextView),
                text.indexOf("|") + 1, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        (holder.getView(R.id.tvAuthorTime) as TextView).text = spanString
    }
}