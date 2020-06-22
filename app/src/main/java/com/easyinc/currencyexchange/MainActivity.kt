package com.easyinc.currencyexchange

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.easyinc.currencyexchange.base.activity.BaseActivity
import com.easyinc.currencyexchange.common.Constant
import com.easyinc.currencyexchange.common.extentions.isNotValid
import com.easyinc.currencyexchange.common.extentions.snack
import com.easyinc.currencyexchange.common.functional.Resource
import kotlinx.android.synthetic.main.activity_main.*
import com.easyinc.currencyexchange.common.utils.PreferenceHelper.get

class MainActivity : BaseActivity() {


    private lateinit var navHostFragment: NavHostFragment
    private lateinit var inflater: NavInflater
    private lateinit var graph: NavGraph


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        navHostFragment = nav_host_fragment as NavHostFragment
        inflater = navHostFragment.navController.navInflater
        graph = inflater.inflate(R.navigation.nav_graph)

        observeMessage()


        checkTokenValidation()

    }

    private fun checkTokenValidation(){
        prevToken = prefs[Constant.TOKEN,""]
        prevLogin = prefs[Constant.LOGIN,""]
        prevTime = prefs[Constant.TIME,0L]

        areValid(prevToken!!,prevLogin!!,prevTime!!)
    }

    private fun areValid(token: String, login: String, time: Long){
        if (token.equals("") || login.equals("") || time.isNotValid())
            nextDestination(false)
        else
            nextDestination(true)
    }

    private fun nextDestination(valid: Boolean){
        when(valid){
            true -> {
                graph.startDestination = R.id.pairsFragment
                navHostFragment.navController.graph = graph
            }
            false -> {
                graph.startDestination = R.id.loginFragment
                navHostFragment.navController.graph = graph
            }
        }
    }

    private fun observeMessage(){
        mainViewModel.observeMessage.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {}
                Resource.Status.ERROR -> {
                    showSnackbar(resources.getString(R.string.error_occurred_try_again) + ", " + it.message)
                    showLoading(false)
                }
                Resource.Status.LOADING -> {showLoading(true)}
            }
        })
    }

}