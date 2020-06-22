package com.easyinc.currencyexchange.presentation.mapper

import com.easyinc.currencyexchange.domain.model.Partner
import com.easyinc.currencyexchange.presentation.model.PartnerView
import javax.inject.Inject

class PartnerViewMapper @Inject constructor(): Mapper<Partner,PartnerView> {

    override fun mapTo(type: PartnerView): Partner {
        return Partner(
            type.Login,
            type.Password
        )
    }

}