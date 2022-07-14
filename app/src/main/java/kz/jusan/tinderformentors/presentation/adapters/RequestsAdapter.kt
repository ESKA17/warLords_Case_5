package kz.jusan.tinderformentors.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.jusan.tinderformentors.R
import kz.jusan.tinderformentors.presentation.models.Match

class RequestsAdapter : RecyclerView.Adapter<RequestsAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    var matches: List<Match>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item_match_mentee,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]

        val tvFullName = holder.itemView.findViewById<TextView>(R.id.tvFullName)
        val tvUniversityOrSchool = holder.itemView.findViewById<TextView>(R.id.tvUniversityOrSchool)
        val tvSubjects = holder.itemView.findViewById<TextView>(R.id.tvSubjects)
        val ibStartChat = holder.itemView.findViewById<ImageButton>(R.id.ibStartChat)

        holder.itemView.apply {
            tvFullName.text = match.authorName
            tvUniversityOrSchool.text = match.university
            tvSubjects.text = match.subjects

            ibStartChat.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("email", match.authorEmail)
                    putString("fullName", match.authorName)
                }
                findNavController().navigate(
                    R.id.action_navigation_requests_to_chatFragment,
                    bundle
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

}