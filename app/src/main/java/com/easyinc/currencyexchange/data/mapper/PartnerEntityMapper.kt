package com.easyinc.currencyexchange.data.mapper

import com.easyinc.currencyexchange.data.model.PartnerEntity
import com.easyinc.currencyexchange.domain.model.Partner
import com.easyinc.currencyexchange.remote.model.PartnerModel
import javax.inject.Inject

class PartnerEntityMapper @Inject constructor(): EntityMapper<PartnerEntity,Partner> {

    override fun mapFrom(entity: PartnerEntity): Partner {
        return Partner(
            entity.Login,
            entity.Password
        )
    }

    override fun mapTo(entity: Partner): PartnerEntity {
        return PartnerEntity(
            entity.Login,
            entity.Password
        )
    }
}