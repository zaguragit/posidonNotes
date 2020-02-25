package posidon.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val context: Context
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)!!
    }

    override fun getItemCount() = Notes.length
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_card, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.textView.text = Notes[i]
        holder.textView.setOnClickListener { Notes.edit(i, context, this) }
    }
}