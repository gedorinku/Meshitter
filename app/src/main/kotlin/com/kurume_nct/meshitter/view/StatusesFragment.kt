package com.kurume_nct.meshitter.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.meshitter.R
import com.kurume_nct.meshitter.databinding.FragmentStatusListBinding
import com.kurume_nct.meshitter.viewmodel.StatusesViewModel
import com.kurume_nct.meshitter.viewmodel.StatusViewModel

class StatusesFragment : Fragment(), StatusesViewModel.Callback, StatusViewModel.Callback {

    private lateinit var binding: FragmentStatusListBinding
    private lateinit var adapter: MyStatusRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_status_list, container, false)
        val recyclerView = view.findViewById(R.id.coordinator_layout).findViewById(R.id.list)

        // Set the adapter
        if (recyclerView is RecyclerView) {
            val context = recyclerView.context
            binding = DataBindingUtil.bind(view)
            binding.viewModel = StatusesViewModel(this, context)
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = MyStatusRecyclerViewAdapter(this, binding.viewModel.statusList)
            recyclerView.adapter = adapter

            binding.viewModel.onCreateView(savedInstanceState)
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        adapter.notifyItemRangeChanged(positionStart, itemCount)
    }

    companion object {

        fun newInstance(): StatusesFragment {
            val fragment = StatusesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
