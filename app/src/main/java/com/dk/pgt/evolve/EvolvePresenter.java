package com.dk.pgt.evolve;

import com.dk.pgt.data.PoGoApi.PoGoApi;
import com.dk.pgt.data.PokeApi.PokemonApi;
import com.dk.pgt.pokemonSearch.PokemonSearchPresenter;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class EvolvePresenter extends PokemonSearchPresenter implements EvolveContract.Presenter {
    private EvolveContract.View view;
    private PoGoApi poGoApi;

    public EvolvePresenter(EvolveContract.View view, PoGoApi poGoApi, PokemonApi pokeApi) {
        super(view, pokeApi);
        this.view = view;
        this.poGoApi = poGoApi;
    }

    @Override
    public void calculateEvolutions(int currentCp) {
        if (mSelectedPokemon == null) {
            view.presentError("Please select a Pokemon");
            return;
        }

        poGoApi.getEvolutions(mSelectedPokemon.getNumber(), currentCp)
                .subscribe(
                        evolutions -> view.showEvolutions(evolutions),
                        throwable -> view.presentError(throwable.getMessage()));
    }
}
