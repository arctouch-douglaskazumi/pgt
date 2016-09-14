package com.dk.pgt.data;

import com.dk.pgt.data.PgoivApi.PoGoIvApi;
import com.dk.pgt.data.PoGoApi.PoGoApi;
import com.dk.pgt.data.PokeApi.PokemonApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by douglaskazumi on 8/20/16.
 */

@Singleton
@Component(modules = DataModule.class)
public interface DataComponent {
    PoGoApi providePoGoApi();
    PokemonApi providePokemonApi();
    PoGoIvApi providePoGoIvApi();
}