package com.easyinc.currencyexchange.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.easyinc.currencyexchange.MainActivity
import com.easyinc.currencyexchange.R
import com.easyinc.currencyexchange.base.fragment.BaseFragment
import com.easyinc.currencyexchange.common.Constant
import com.easyinc.currencyexchange.common.extentions.snack
import com.easyinc.currencyexchange.common.functional.Resource
import com.easyinc.currencyexchange.common.utils.PreferenceHelper.set
import com.easyinc.currencyexchange.ui.model.Partner
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment() {

    private lateinit var mUsername: String
    private lateinit var mPassword: String

    override fun layoutId(): Int {
        return R.layout.fragment_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = (activity as MainActivity)
        mainViewModel = mainActivity.mainViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        compositeDisposable.add(
            login.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(
                {
                    handleOnLoginClick()
                },{
                    view.snack(resources.getString(R.string.error_occurred_try_again))
                }
            )
        )

    }

    private fun handleOnLoginClick(){
        mUsername = username.text.toString()
        mPassword = password.text.toString()
        when{
            areEmptyFields(mUsername,mPassword) -> mainActivity.showSnackbar(resources.getString(R.string.required_data))

            !isConnected -> mainActivity.showSnackbar(resources.getString(R.string.check_internet))

            else -> handleRequest(
                Partner(mUsername,mPassword)
            )
        }
    }

    private fun areEmptyFields(username: String, password: String): Boolean{
        return TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
    }

    private fun handleRequest(partner: Partner) {
        observeRequest()
        mainViewModel.loginPartner(
            partnerMapper.mapTo(partner)
        )
    }

    private fun observeRequest(){
        mainViewModel.observeLoginToken.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.LOADING -> {}

                Resource.Status.ERROR -> {}

                Resource.Status.SUCCESS -> {
                    val token = it.data
                    showLoading(false)

                    prefs[Constant.TOKEN] = token
                    prefs[Constant.LOGIN] = mUsername
                    prefs[Constant.TIME] = System.currentTimeMillis()

                    if (navController.currentDestination?.id == R.id.loginFragment) {
                        navController.navigate(R.id.action_loginFragment_to_pairsFragment)
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


}