package ariel.paba.kalkulator

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HistoryAdapter(
    context: Context,
    history: MutableList<String>
) : ArrayAdapter<String>(
    context,
    android.R.layout.simple_list_item_1,
    history
) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val row = super.getView(position, convertView, parent) as TextView

        val result = getItem(position)
            ?.substringAfterLast("=")
            ?.trim()
            ?.replace(',', '.')
            ?.toDoubleOrNull()

        row.setBackgroundColor(
            if (result != null && result < 0) Color.RED
            else Color.TRANSPARENT
        )

        return row
    }
}