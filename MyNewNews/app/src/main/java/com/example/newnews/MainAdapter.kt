package com.example.newnews

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*
import java.text.SimpleDateFormat

class MainAdapter(private val model: Model): RecyclerView.Adapter<CustomViewHolder>() {

    // Return number of items in the cell
    override fun getItemCount(): Int {
        return model.articles.size
    }

    // Renders the cell row with data info
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return  CustomViewHolder(cellForRow)
    }

    // Populates-binds the view with json dat info fetched using Picasso library
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val article = model.articles.get(position)
        holder.itemView.textView_videoTitle.text = article.title


        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val dateString = formatter.parse(article.publishedAt)

        holder.itemView.textView_channelName.text = dateString.toString()

        val thumbnailArticleImageView = holder.itemView.imageView_videoThumbnail
        Picasso.get().load(article.urlToImage).into(thumbnailArticleImageView)

        holder.articles = article

    }

}


// TAP ROW: - Custom view holder use an intent to present and pass data from activity to activity
class CustomViewHolder(view: View, var articles: Articles? = null): RecyclerView.ViewHolder(view) {

    companion object {
        const val URL_SITE_LINK = "url_link"
    }

    init {
        itemView.setOnClickListener {

            val intent = Intent(itemView.context, CourseDetailActivity::class.java)

            intent.putExtra(URL_SITE_LINK, articles?.url)

            itemView.context.startActivity(intent)
        }
    }
}