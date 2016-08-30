package com.dk.pgt.evolve;

import com.dk.pgt.data.PoGoApi.PoGoApi;
import com.dk.pgt.data.PokeApi.PokemonApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by douglaskazumi on 8/23/16.
 */

@Module
public class EvolveModule {

    private EvolveContract.View mView;

    public EvolveModule(EvolveContract.View view) {
        mView = view;
    }

    @Provides
    EvolveContract.View provideView() {
        return mView;
    }

    @Provides
    EvolveContract.Presenter providePresenter(EvolveContract.View view, PoGoApi poGoApi,
                                              PokemonApi pokeApi) {
        return new EvolvePresenter(view, poGoApi, pokeApi);
    }
}
