package posidon.notes

import android.app.Dialog
import android.os.Bundle
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.EditText
import android.widget.PopupWindow
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.main.*

class Main : AppCompatActivity() {

    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        Notes.init(this)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        NoteAdapter(this).let {
            recycler.adapter = it
            adapter = it
        }

        fab.setOnClickListener {
            val d = Dialog(this)
            d.setContentView(R.layout.note_edition)
            d.window!!.apply {
                setBackgroundDrawableResource(android.R.color.transparent)
                setLayout(MATCH_PARENT, resources.displayMetrics.heightPixels / 2)
            }
            d.findViewById<View>(R.id.done_btn).setOnClickListener {
                Notes.add(d.findViewById<EditText>(R.id.text).text.toString())
                d.dismiss()
                adapter.notifyDataSetChanged()
                Notes.save(this)
            }
            d.findViewById<View>(R.id.cancel_btn).setOnClickListener { d.dismiss() }
            d.show()
        }
    }
}
