package kz.jusan.tinderformentors.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.jusan.tinderformentors.R
import kz.jusan.tinderformentors.presentation.models.Card


class CardStackAdapter : RecyclerView.Adapter<CardStackAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    var cards: List<Card>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_card_tinder,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]

        val ivAvatar = holder.itemView.findViewById<ImageView>(R.id.ivAvatar)
        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)

        holder.itemView.apply {
            tvName.text = card.name
            ivAvatar.setImageResource(card.avatar)
        }
    }

    override fun getItemCount(): Int = cards.size

}