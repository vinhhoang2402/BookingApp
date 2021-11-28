package com.example.bookingapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookingapp.R
import com.example.bookingapp.common.Resource.Injector
import com.example.bookingapp.common.Resource.Status
import com.example.bookingapp.ui.adapter.ParentAdapter
import com.example.bookingapp.ui.model.MoviesResponse
import com.example.bookingapp.ui.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_nested.*

class NestedFragment : Fragment() {

    private lateinit var vm: MovieViewModel
    private lateinit var adapter: ParentAdapter
    private val movieList: ArrayList<MoviesResponse> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nested, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init
        vm = ViewModelProvider(this, Injector.GetFlowPagingSource()).get(
            MovieViewModel::class.java)

        initView()
        updateData()
    }

    private fun updateData() {
        vm.popular.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.addAll(it.data!!)
                    Log.d("AAAAAAAAAAA", movieList.toString())
                }
            }
        })


        vm.rate.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.addAll(it.data!!)
                    Log.d("AAAAAAAAAAA", movieList.toString())
                }
            }
        })


        vm.trend.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.addAll(it.data!!)
                    Log.d("AAAAAAAAAAA", movieList.toString())
                }
            }
        })
    }

    private fun initView() {
        adapter = ParentAdapter()
        rc_nested.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        rc_nested.adapter = adapter
    }
}