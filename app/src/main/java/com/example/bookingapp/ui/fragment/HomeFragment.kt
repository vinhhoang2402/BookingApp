package com.example.bookingapp.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookingapp.common.Resource.Injector
import com.example.bookingapp.databinding.FragmentHomeBinding
import com.example.bookingapp.ui.adapter.PlayersLoadingStateAdapter
import com.example.bookingapp.ui.adapter.PopularAdapter
import com.example.bookingapp.ui.viewmodel.PopularViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var popularBinding: FragmentHomeBinding
    private lateinit var mPopularViewModel: PopularViewModel
    private var recyclerviewState: Parcelable? = null
    private var selectedPosition: Int = 0
    private var popularAdapter = PopularAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        popularBinding = FragmentHomeBinding.inflate(inflater)
        return popularBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getPopular()
        mPopularViewModel = ViewModelProvider(this, Injector.GetFlowPagingSource()).get(
            PopularViewModel::class.java
        )
        // mPopularViewModel = ViewModelProvider(this,factory).get(PopularViewModel::class.java)
        initView()
        lifecycleScope.launch {
            mPopularViewModel.listHotel.collectLatest { data ->
                popularAdapter.submitData(data)
            }
        }

        mPopularViewModel.getList()

        // mPopularViewModel.getAlphabeticOrderTabs()
        // mPopularViewModel.getPopular()
        //initData()
    }

    private fun initView() {
        popularBinding.rcPopular.apply {
            adapter = popularAdapter.withLoadStateFooter(
                footer = PlayersLoadingStateAdapter { popularAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(context)

            setFadingEdgeLength(100)
            scrollToPosition(mPopularViewModel.getPopularSortPositions(selectedPosition))
            isNestedScrollingEnabled = false

            lifecycleScope.launch {
                popularAdapter.loadStateFlow.collectLatest {
                    if (it.prepend is LoadState.NotLoading && it.prepend.endOfPaginationReached) {
                        delay(5000)
                        popularBinding.progressLoading.visibility = View.GONE
                    } else {
                        popularBinding.progressLoading.visibility = View.VISIBLE
                    }
                }
            }

            popularAdapter.addLoadStateListener { loadState ->

                if (loadState.mediator?.refresh is LoadState.Loading) {

                    if (popularAdapter.snapshot().isEmpty()) {
                        progress_loading.visibility = View.VISIBLE
                    }
                    progress_loading.visibility = View.VISIBLE

                } else {
                   // progress_loading.visibility = View.GONE
                }
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    val myLayoutManager = layoutManager
//                    val scrollPositionFirstItem =
//                        (myLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
//                    val position = mPopularViewModel.getTabPosition(scrollPositionFirstItem)
//                    if (position != -1) {
//                        val currentTab = popularBinding.tabAlpha.getTabAt(position)
//                        currentTab?.let { tab ->
//                            tab.select()
//                        }
//                    }
//                }
//            })
            }
        }
    }

    private fun initData() {
        mPopularViewModel.mAlphabeticOrderTabs.observe(viewLifecycleOwner, { tabs ->
            popularBinding.tabAlpha.removeAllTabs()
            tabs.forEach { title -> createTab(title) }
            updateTabs()
            popularBinding.tabAlpha.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    Log.d("onTabSelected", "Tab position: ${tab.position}")
                    val position = mPopularViewModel.getPopularSortPositions(tab.position)
                    (popularBinding.rcPopular.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                        position,
                        0
                    )
                    if (recyclerviewState != null) {
                        (popularBinding.rcPopular.layoutManager as LinearLayoutManager).onRestoreInstanceState(
                            recyclerviewState
                        )
                        recyclerviewState = null
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
            val currentTab = popularBinding.tabAlpha.getTabAt(selectedPosition)
            currentTab?.select()
        })
//        mPopularViewModel.popular.observe(viewLifecycleOwner, {list ->
//            Log.d("initData", list.toString())
//            Observable.timer(1, MILLISECONDS)
//                .subscribe({
//                    popularBinding.progressLoading.visibility = View.GONE
//                    popularAdapter.updateItems(list)
//                }, {
//                    LogUtil.v(TAG, "timer", "error: ${it.message}")
//                })
//        })
    }

    private fun createTab(title: Char) {
        Log.d("createTab", title.toString())
        val tab = popularBinding.tabAlpha.newTab().also {
            it.text = title.toString()
        }
        popularBinding.tabAlpha.addTab(tab)
    }

    private fun updateTabs() {
        val tabCount = popularBinding.tabAlpha.tabCount
        val mainView = popularBinding.tabAlpha.getChildAt(0) as ViewGroup
        for (i in 0 until tabCount - 1) {
            val tabView = mainView.getChildAt(i) as ViewGroup
            val p = tabView.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, 50, 0)
            tabView.requestLayout()
        }
        popularBinding.tabAlpha.requestLayout()
    }

//    private fun getPopular() {
//        val jsonPopular = getJsonDataFromAsset<Popular>(requireContext(), "popular.json")
//        jsonPopular?.results?.forEach {
//            mPopularViewModel.data.addAll(it.knownFor)
//        }
//        Log.d("getPopular", mPopularViewModel.data.toString())
//    }
}