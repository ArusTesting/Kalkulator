package ariel.paba.kalkulator

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class MultiplyFragment : Fragment() {
    fun refresh() {
        (view as? ListView)?.let {
            (it.adapter as? ArrayAdapter<*>)?.notifyDataSetChanged()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listView = ListView(requireContext())

        listView.adapter = HistoryAdapter(
            requireContext(),
            HistoryData.multiplyHistory
        )

        return listView
    }
}