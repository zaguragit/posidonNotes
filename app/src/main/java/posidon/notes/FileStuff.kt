package posidon.notes

import android.content.Context
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.util.*

object FileStuff {

    private const val NOTES_FILENAME = "notes"

    fun writeData(
        notes: ArrayList<String>,
        context: Context
    ) {
        try {
            val fos = context.openFileOutput(NOTES_FILENAME, Context.MODE_PRIVATE)
            val oos = ObjectOutputStream(fos)
            oos.writeObject(notes)
            oos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readData(context: Context): ArrayList<String> = try {
        val fis = context.openFileInput(NOTES_FILENAME)
        val ois = ObjectInputStream(fis)
        ois.readObject() as ArrayList<String>
    } catch (e: Exception) { ArrayList() }
}