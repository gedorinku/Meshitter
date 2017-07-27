package com.kurume_nct.meshitter.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.meshitter.BR
import com.kurume_nct.meshitter.R
import com.kurume_nct.meshitter.viewmodel.StatusesViewModel
import com.kurume_nct.meshitter.viewmodel.StatusViewModel
import twitter4j.Status

class MyStatusRecyclerViewAdapter(
        private val callback: StatusViewModel.Callback,
        private val statuses: List<Status>)
    : RecyclerView.Adapter<MyStatusRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_status, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(
                BR.viewModel,
                StatusViewModel(callback, statuses[position])
        )
    }

    override fun getItemCount(): Int {
        return statuses.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: ViewDataBinding = DataBindingUtil.bind(view)
    }
}
