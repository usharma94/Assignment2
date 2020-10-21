package ca.sheridan.sharmupm.assignment2.ui.history

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ca.sheridan.sharmupm.assignment2.R
import ca.sheridan.sharmupm.assignment2.database.GameScore


class HistoryRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>() {
    var history: List<GameScore>? = null
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = history!![position]
        holder.idView.text = "${position + 1}."
        holder.contentView.text = "${item.die1} + ${item.die2} + ${item.die3} = ${item.total}"
    }

    override fun getItemCount(): Int = history?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}