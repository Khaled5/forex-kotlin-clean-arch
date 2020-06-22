package com.easyinc.currencyexchange.remote.mapper

import com.easyinc.currencyexchange.data.model.PartnerEntity
import com.easyinc.currencyexchange.remote.model.PartnerModel
import javax.inject.Inject

class PartnerModelMapper @Inject constructor(): ModelMapper<PartnerEntity,PartnerModel> {
    override fun mapFrom(map: PartnerEntity): PartnerModel {
        return PartnerModel(
            map.Login,
            map.Password
        )
    }
}