package com.dk.pgt.data.PgoivApi

import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by douglaskazumi on 9/13/16.
 */
class PgoivApi(val api: PgoivRetrofit) : PoGoIvApi {
    val BASE_URL: String = "http://www.pgoiv.com/api/public/pokemon/"

    override fun getIvData(pokemonName: String, currentCp: Int, currentHp: Int, requiredDust: String, trainerLevel: Int, isPowered: Boolean): Observable<PgoivApiResponse> {
        val pgoivRequestData = PgoivRequestData(
                pokemonName, currentCp, currentHp,
                requiredDust, trainerLevel, isPowered)

        return api.getIvData(pgoivRequestData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    interface PgoivRetrofit {
        @POST("getCombinas")
        fun getIvData(@Body requestData: PgoivRequestData): Observable<PgoivApiResponse>
    }
}

data class PgoivRequestData(
        val name: String, val cp: Int, val hp: Int,
        val dust: String, val trainerLevel: Int, val isPowered: Boolean)
