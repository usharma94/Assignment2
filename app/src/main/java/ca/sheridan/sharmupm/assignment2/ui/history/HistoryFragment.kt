package ca.sheridan.sharmupm.assignment2.ui.history

import android.app.Activity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.sheridan.sharmupm.assignment2.R
import ca.sheridan.sharmupm.assignment2.database.GameScore
import ca.sheridan.sharmupm.assignment2.databinding.FragmentHistoryBinding


/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryRecyclerViewAdapter
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var navController: NavController

    //    add clear menu to fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        adapter = HistoryRecyclerViewAdapter(requireContext())
        with(binding){
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(divider)
            recyclerView.adapter = adapter

        }
        navController = findNavController()
        viewModel.history.observe(viewLifecycleOwner){ refreshHistory(it) }
        return binding.root
    }
    private fun refreshHistory(list: List<GameScore>?) {
        adapter.history = list
        if (list != null) {
            val sum = list.sumBy {it.total}
            binding.txtTotal.text =
                    resources.getQuantityString(R.plurals.history_total, sum, sum)
        }else {
//            val count = list?.size ?: 0
            val count = 0
            binding.txtTotal.text =
                    resources.getQuantityString(R.plurals.history_total, count, count)
        }

    }

    //load menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
    }
    //navigate to history fragment
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_clear -> {
                viewModel.clear()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    private fun clear(){
        viewModel.clear()
    }


}