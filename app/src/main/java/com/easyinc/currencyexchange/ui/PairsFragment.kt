package com.easyinc.currencyexchange.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.easyinc.currencyexchange.MainActivity
import com.easyinc.currencyexchange.R
import com.easyinc.currencyexchange.base.fragment.BaseFragment
import com.easyinc.currencyexchange.common.Constant
import com.easyinc.currencyexchange.common.extentions.snack
import com.easyinc.currencyexchange.common.functional.Resource
import com.easyinc.currencyexchange.common.utils.InfiniteScrollListener
import com.easyinc.currencyexchange.ui.adapter.ForexAdapter
import kotlinx.android.synthetic.main.fragment_pairs.*
import com.easyinc.currencyexchange.common.utils.PreferenceHelper.get
import com.easyinc.currencyexchange.ui.adapter.LoadingAdapter
import com.easyinc.mappractice.common.Logger
import javax.inject.Inject

class PairsFragment : BaseFragment() {

    private var token: String? = null
    private var login: String? = null
    private var time: Long? = null

    @Inject lateinit var forexAdapter: ForexAdapter
    @Inject lateinit var loadingAdapter: LoadingAdapter

    private lateinit var forexMergeAdapter: MergeAdapter

    override fun layoutId(): Int {
        return R.layout.fragment_pairs
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = (activity as MainActivity)
        mainViewModel = mainActivity.mainViewModel

        token = prefs[Constant.TOKEN,""]
        login = prefs[Constant.LOGIN,""]
        time = prefs[Constant.TIME,0L]


        if (!token.equals("") || token != null){
            showLoading(true)
            mainViewModel.getPairs(token!!,login!!)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initRecycler()

        observePairs()

        observeMorePairs()

        logoutPartner()

    }

    private fun logoutPartner(){
        logout.setOnClickListener {
            navController.navigate(R.id.action_pairsFragment_to_loginFragment)
            prefs.edit().clear().apply()
        }
    }

    private fun initRecycler(){

        forexMergeAdapter = MergeAdapter(forexAdapter,loadingAdapter)

        pairs_recycler.apply {
            adapter = forexMergeAdapter
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            setHasFixedSize(true)
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({
                mainViewModel.getMorePairs(token!!,login!!) }, linearLayout))

        }
    }

    private fun observeMorePairs(){
        mainViewModel.observeMorePairs.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    showLoading(false)
                    forexAdapter.submitList(
                        it.data!!.map { currency ->
                            currencyMapper.mapTo(currency)
                        })
                    forexMergeAdapter.removeAdapter(loadingAdapter)
                }

                Resource.Status.ERROR -> {}

                Resource.Status.LOADING -> {forexMergeAdapter.addAdapter(loadingAdapter)}
            }
        })
    }

    private fun observePairs(){
        mainViewModel.observePairs.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    showLoading(false)
                    if (it.data!!.isNotEmpty()) {
                        forexAdapter.submitList(
                            it.data.map { currency ->
                                currencyMapper.mapTo(currency)
                            })
                    }else {
                        forexMergeAdapter.removeAdapter(loadingAdapter)
                    }
                }

                Resource.Status.ERROR -> {}

                Resource.Status.LOADING -> {}
            }
        })
    }

}