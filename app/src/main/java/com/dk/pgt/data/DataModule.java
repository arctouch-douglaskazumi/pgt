package com.dk.pgt.data;

import android.util.Log;

import com.dk.pgt.PGTApp;
import com.dk.pgt.data.PgoivApi.PgoivApi;
import com.dk.pgt.data.PgoivApi.PoGoIvApi;
import com.dk.pgt.data.PoGoApi.GameInfoApi;
import com.dk.pgt.data.PoGoApi.PoGoApi;
import com.dk.pgt.data.PokeApi.MockPokeApi;
import com.dk.pgt.data.PokeApi.PokemonApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by douglaskazumi on 8/20/16.
 */

@Module
public class DataModule {

    public DataModule() {
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request request = chain.request();

            okhttp3.Response response = chain.proceed(request);
            String rawJson = response.body().string();

            Log.d(PGTApp.PGT, String.format("raw JSON response is: %s", rawJson));

            // Re-create the response before returning it because body can be read only once
            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), rawJson)).build();
        });
        return httpClient.build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);
    }

    @Provides
    @Singleton
    PoGoApi providePoGoApi(Retrofit.Builder builder) {
        GameInfoApi.GameInfoRetrofit gameInfoRetrofit = builder
                .baseUrl(GameInfoApi.BASE_URL)
                .build()
                .create(GameInfoApi.GameInfoRetrofit.class);
        return new GameInfoApi(gameInfoRetrofit);
    }

    @Provides
    @Singleton
    PokemonApi providePokemonApi(Retrofit.Builder builder) {
//        PokeApi.PokeApiRetrofit pokeApiRetrofit = builder
//                .baseUrl(PokeApi.BASE_URL)
//                .build()
//                .create(PokeApi.PokeApiRetrofit.class);
//        return new PokeApi(pokeApiRetrofit);
        return new MockPokeApi();
    }

    @Provides
    @Singleton
    PoGoIvApi providePoGoIvApi(Retrofit.Builder builder) {
        PgoivApi.PgoivRetrofit pgoivRetrofit = builder
//                .baseUrl(PgoivApi.BASE_URL) //Kotlin Java interoperability error
                .baseUrl("http://www.pgoiv.com/api/public/pokemon/")
                .build()
                .create(PgoivApi.PgoivRetrofit.class);
        return new PgoivApi(pgoivRetrofit);
    }
}
