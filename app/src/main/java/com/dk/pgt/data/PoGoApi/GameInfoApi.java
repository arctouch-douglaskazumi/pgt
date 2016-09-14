package com.dk.pgt.data.PoGoApi;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by douglaskazumi on 8/18/16.
 */
public class GameInfoApi implements PoGoApi {
    public static final String BASE_URL = "https://api.gameinfo.io/tools/";

    private GameInfoRetrofit mApi;

    public GameInfoApi(GameInfoRetrofit retrofit) {
        this.mApi = retrofit;
    }

    @Override
    public Observable<List<Evolution>> getEvolutions(int pokemonId, int currentCp) {
        return mApi.evolutionCalculator(pokemonId, currentCp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public interface GameInfoRetrofit {
        @FormUrlEncoded
        @POST("evolution-calculator")
        Observable<List<Evolution>> evolutionCalculator(@Field("p") int p, @Field("cp") int cp);
    }
}
