package com.easyinc.currencyexchange.ui.mapper

import com.easyinc.currencyexchange.presentation.model.PartnerView
import com.easyinc.currencyexchange.ui.model.Partner
import javax.inject.Inject

class PartnerMapper @Inject constructor(): ViewMapper<Partner, PartnerView> {
    override fun mapTo(type: Partner): PartnerView {
        return PartnerView(
            type.Login,
            type.Password
        )
    }
}