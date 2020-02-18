package posidon.notes

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

object Notes {

    private lateinit var notes: ArrayList<String>

    fun init(context: Context) {
        notes = FileStuff.readData(context)
    }

    operator fun get(i: Int) = notes[i]
    operator fun set(i: Int, str: String) { notes[i] = str }

    fun add(str: String) = notes.add(str)

    val length: Int get() = notes.size

    fun save(context: Context) = FileStuff.writeData(notes, context)

    fun edit(i: Int, context: Context, adapter: NoteAdapter) {
        val d = Dialog(context)
        d.setContentView(R.layout.note_edition)
        d.window!!.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, context.resources.displayMetrics.heightPixels / 2)
        }
        d.findViewById<EditText>(R.id.text).setText(get(i))
        d.findViewById<View>(R.id.done_btn).setOnClickListener {
            set(i, d.findViewById<EditText>(R.id.text).text.toString())
            d.dismiss()
            adapter.notifyItemChanged(i)
            save(context)
        }
        d.findViewById<View>(R.id.cancel_btn).setOnClickListener { d.dismiss() }
        d.findViewById<View>(R.id.destructive_btn).apply {
            visibility = View.VISIBLE
            setOnClickListener {
                notes.removeAt(i)
                d.dismiss()
                adapter.notifyDataSetChanged()
                save(context)
            }
        }
        d.show()
    }
}