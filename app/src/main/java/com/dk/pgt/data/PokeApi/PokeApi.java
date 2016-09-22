package com.dk.pgt.data.PokeApi;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class PokeApi implements PokemonApi {
    public static final String BASE_URL = "http://pokeapi.co/api/v2/";

    PokeApiRetrofit mApi;

    public PokeApi(PokeApiRetrofit retrofit) {
        this.mApi = retrofit;
    }

    @Override
    public Observable<PokeApiResponse<Pokemon>> getPokemons(Integer limit, Integer offset) {
        return mApi.getPokemons(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public interface PokeApiRetrofit {
        @GET("pokemon/")
        Observable<PokeApiResponse<Pokemon>> getPokemons(@Query("limit") Integer limit, @Query
                ("offset") Integer offset);
    }

    public class MockApi extends PokeApiResponse<Pokemon> {
    }
}
