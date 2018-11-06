package com.raphanum.habrrss.ui

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.raphanum.habrrss.R
import com.raphanum.habrrss.model.HabrItem
import com.raphanum.habrrss.util.extractImg
import com.raphanum.habrrss.util.removeImgTags
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.feed_item_view.*

class FeedListAdapter : RecyclerView.Adapter<FeedListAdapter.FeedItemViewHolder>() {

    private var items: List<HabrItem>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedItemViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.feed_item_view, parent, false)
        return FeedItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder?, position: Int) {
        holder?.bind(items?.get(position))
    }

    fun setItems(items: List<HabrItem>?) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class FeedItemViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: HabrItem?) {
            title.text = item?.title
            item?.categories?.let {
                val sb = StringBuilder()
                for (category in it) {
                    if (sb.isEmpty().not()) {
                        sb.append(", ")
                    }
                    sb.append(category.text)
                }
                categories.text = sb.toString()
            }

            var text = item?.description
            val extractImg = text?.extractImg()
            if (extractImg != null) {
                Glide.with(itemView.context)
                    .asBitmap()
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
                    .load(extractImg.link)
                    .into(image)

                text = text?.removeImgTags()

            } else {
                image.setImageDrawable(null)
            }

            description.movementMethod = LinkMovementMethod.getInstance()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                description.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
            } else {
                description.text = Html.fromHtml(text)
            }
            author.text = item?.author
        }
    }
}