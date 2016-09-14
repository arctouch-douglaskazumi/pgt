package com.dk.pgt.iv;

import com.dk.pgt.data.PgoivApi.PoGoIvApi;
import com.dk.pgt.data.PokeApi.PokemonApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by douglaskazumi on 8/23/16.
 */

@Module
public class IvModule {

    private IvContract.View mView;

    public IvModule(IvContract.View view) {
        mView = view;
    }

    @Provides
    IvContract.View provideView() {
        return mView;
    }

    @Provides
    IvContract.Presenter providePresenter(IvContract.View view, PoGoIvApi poGoIvApi,
                                              PokemonApi pokeApi) {
        return new IvPresenter(view, poGoIvApi, pokeApi);
    }
}
