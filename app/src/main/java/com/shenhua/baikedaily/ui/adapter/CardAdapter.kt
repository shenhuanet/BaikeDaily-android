package com.shenhua.baikedaily.ui.adapter

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.widget.TextView
import com.shenhua.baikedaily.R
import java.util.*


/**
 * Created by shenhua on 2017-12-13-0013.
 * @author shenhua
 *         Email shenhuanet@126.com
 */
class CardAdapter(mContext: Context, datas: ArrayList<String>?) : BaseRecyclerAdapter<String>(mContext, datas) {

    override fun getItemViewId(viewType: Int): Int {
        return R.layout.item_content
    }

    override fun bindData(holder: BaseRecyclerViewHolder, position: Int, item: String) {
        val text = "du 小力 |\n2017.12.12"
        val spanString = SpannableString(text)
        spanString.setSpan(TextAppearanceSpan(mContext, R.style.TimeTextView), text.indexOf("|") + 1, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        (holder.getView(R.id.tvAuthorTime) as TextView).text = spanString
    }
}