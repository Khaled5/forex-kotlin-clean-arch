package com.easyinc.currencyexchange.remote.mapper

interface ModelMapper<in M, out E> {

    fun mapFrom(map: M) : E
}