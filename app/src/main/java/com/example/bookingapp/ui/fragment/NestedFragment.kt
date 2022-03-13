package com.example.bookingapp.ui.fragment

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookingapp.R
import com.example.bookingapp.common.Constants
import com.example.bookingapp.common.Resource.Injector
import com.example.bookingapp.common.Resource.Status
import com.example.bookingapp.ui.adapter.ParentAdapter
import com.example.bookingapp.ui.model.MoviesResponse
import com.example.bookingapp.ui.viewmodel.MovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_nested.*
import kotlinx.android.synthetic.main.item_bottom_sheet.*

class NestedFragment : Fragment() {

    private lateinit var vm: MovieViewModel
    private lateinit var adapter: ParentAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog
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
                    if(it.data != null) adapter.addAll(it.data,"Popular")
                    Log.d("AAAAAAAAAAA", movieList.toString())
                }
            }
        })


        vm.rate.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if(it.data != null) adapter.addAll(it.data, "Rate")
                    Log.d("AAAAAAAAAAA", movieList.toString())
                }
            }
        })


        vm.trend.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if(it.data != null) adapter.addAll(it.data, "Trending")
                    Log.d("AAAAAAAAAAA", movieList.toString())
                }
            }
        })

        vm.latest.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if(it.data != null) adapter.addAll(it.data, "Latest")
                    Log.d("AAAAAAAAAAA", it.toString())
                }
            }
        })
    }

    private fun initView() {
        adapter = ParentAdapter(onItemClick)
        rc_nested.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        rc_nested.adapter = adapter
        bottomSheetDialog = BottomSheetDialog(requireContext());
    }

    private val onItemClick: (MoviesResponse.Movie) -> Unit = {
        showBottomSheet(it)
    }

    private fun showBottomSheet(data: MoviesResponse.Movie) {
        bottomSheetDialog.setContentView(R.layout.item_bottom_sheet)
        val title = bottomSheetDialog.findViewById<TextView>(R.id.titles)
        val image = bottomSheetDialog.findViewById<ImageView>(R.id.image)
        val des = bottomSheetDialog.findViewById<TextView>(R.id.des)
        image?.let {
            Glide.with(requireContext()).load(Constants.URL_IMAGE.plus(data.backdropPath)).into(
                it
            )
        }
        title?.text = data.originalTitle
        des?.text = data.overview
        bottomSheetDialog.show()
    }
}