package com.easyinc.currencyexchange.base.activity

import android.content.SharedPreferences
import android.view.View
import com.easyinc.currencyexchange.R
import com.easyinc.currencyexchange.base.viewmodel.factory.ViewModelFactory
import com.easyinc.currencyexchange.base.viewmodel.factory.getViewModel
import com.easyinc.currencyexchange.common.extentions.action
import com.easyinc.currencyexchange.common.extentions.androidLazy
import com.easyinc.currencyexchange.common.extentions.snack
import com.easyinc.currencyexchange.presentation.ForexViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

open class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var prefs: SharedPreferences

    var prevTime: Long? = null
    var prevToken: String? = null
    var prevLogin: String? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ForexViewModel>
    val mainViewModel by androidLazy {
        getViewModel<ForexViewModel>(viewModelFactory)
    }

    fun showLoading(show: Boolean){
        loading_mask.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showSnackbar(message: String, withAction: Boolean = false){
        if (withAction)
            snackbarWithAction(message)
        else
            product_details_snack.snack(message)
    }

    private fun snackbarWithAction(message: String){
        product_details_snack.snack(message){
            action(resources.getString(R.string.try_again)){
                mainViewModel.getPairs(prevToken!!,prevLogin!!)
            }
        }
    }

}