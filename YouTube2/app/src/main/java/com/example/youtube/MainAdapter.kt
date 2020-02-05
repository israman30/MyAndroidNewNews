package com.example.youtube

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val model: Model): RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("One", "Two", "Three")

    // Return number of items in the cell
    override fun getItemCount(): Int {
        return model.articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return  CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val article = model.articles.get(position)
        holder.itemView.textView_videoTitle.text = article.title

        holder.itemView.textView_channelName.text = "by " + article.publishedAt

        val thumbnailArticleImageView = holder.itemView.imageView_videoThumbnail
        Picasso.get().load(article.urlToImage).into(thumbnailArticleImageView)

        holder.articles = article

    }

}

class CustomViewHolder(view: View, var articles: Articles? = null): RecyclerView.ViewHolder(view) {

    companion object {
        val URL_SITE_LINK = "url_link"
    }
    init {
        itemView.setOnClickListener {
            println("TEST")

            val intent = Intent(itemView.context, CourseDetailActivity::class.java)

            intent.putExtra(URL_SITE_LINK, articles?.url)

            itemView.context.startActivity(intent)
        }
    }
}