package kz.jusan.tinderformentors.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.jusan.tinderformentors.R
import kz.jusan.tinderformentors.presentation.models.Post

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    var posts: List<Post>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]

        val tvFullName = holder.itemView.findViewById<TextView>(R.id.tvFullName)
        val tvUniversityOrSchool = holder.itemView.findViewById<TextView>(R.id.tvUniversityOrSchool)
        val tvNewsContent = holder.itemView.findViewById<TextView>(R.id.tvNewsContent)

        holder.itemView.apply {
            tvFullName.text = post.authorName
            tvUniversityOrSchool.text = post.university
            tvNewsContent.text = post.message
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}