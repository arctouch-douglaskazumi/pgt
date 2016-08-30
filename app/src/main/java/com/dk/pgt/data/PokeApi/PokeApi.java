package com.dk.pgt.data.PokeApi;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class PokeApi implements PokemonApi {
    private static final String BASE_URL = "http://pokeapi.co/api/v2/";

    PokeApiRetrofit mApi;

    public PokeApi(Retrofit.Builder retrofit) {
        this.mApi = retrofit
                .baseUrl(BASE_URL).build()
                .create(PokeApiRetrofit.class);
    }

    @Override
    public Observable<PokeApiResponse<Pokemon>> getPokemons(Integer limit, Integer offset) {
        return mApi.getPokemons(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    interface PokeApiRetrofit {
        @GET("pokemon/")
        Observable<PokeApiResponse<Pokemon>> getPokemons(@Query("limit") Integer limit, @Query
                ("offset") Integer offset);
    }
}
