package com.example.languagelearningquiz

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.languagelearningquiz.data.LanguageUser
import com.example.languagelearningquiz.databinding.ScoreRowBinding

class ScoreAdapter(
    private val context: Context
) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    var scoreList: MutableList<LanguageUser> = mutableListOf<LanguageUser>(
    )

    fun addUser(user: LanguageUser) {
        scoreList.add(user)
        notifyItemInserted(scoreList.lastIndex)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val scoreRowBinding = ScoreRowBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )
        return ViewHolder(scoreRowBinding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentScore = scoreList[position]

        // sets the text to the textview from our itemHolder class
        holder.bind(currentScore)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return scoreList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(private val rowBinding: ScoreRowBinding) :
        RecyclerView.ViewHolder(rowBinding.root) {

        fun bind(sc: LanguageUser) {
            rowBinding.tvName.text = sc.displayName
            if (sc.language == "Chinese") {
                rowBinding.tvScore.text = sc.ChScore.toString()
                rowBinding.ivIcon.setImageResource(R.drawable.chinese)
            }
            else {
                rowBinding.tvScore.text = sc.SpScore.toString()
                rowBinding.ivIcon.setImageResource(R.drawable.spanish)
            }
        }
    }
}
