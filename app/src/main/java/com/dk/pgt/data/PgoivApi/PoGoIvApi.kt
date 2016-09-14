package com.dk.pgt.data.PgoivApi

import rx.Observable

/**
 * Created by douglaskazumi on 9/13/16.
 */
interface PoGoIvApi {
    fun getIvData(pokemonName: String, currentCp: Int, currentHp: Int, requiredDust: String,
                  trainerLevel: Int, isPowered: Boolean) : Observable<PgoivApiResponse>
}