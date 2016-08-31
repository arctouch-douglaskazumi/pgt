package com.dk.pgt.evolve;

import com.dk.pgt.data.PoGoApi.PoGoApi;
import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.data.PokeApi.PokemonApi;

import java.util.List;

import rx.Observable;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class EvolvePresenter implements EvolveContract.Presenter {
    private static final int MAX_SUGGESTIONS = 5;
    private EvolveContract.View mView;
    private PoGoApi mPoGoApi;
    private PokemonApi mPokeApi;
    private List<Pokemon> mPokemons;
    private List<Pokemon> mFilteredPokemons;
    private Pokemon mSelectedPokemon;

    public EvolvePresenter(EvolveContract.View view, PoGoApi poGoApi, PokemonApi pokeApi) {
        mView = view;
        mPoGoApi = poGoApi;
        mPokeApi = pokeApi;
    }

    @Override
    public void calculateEvolutions(int currentCp) {
        if (mSelectedPokemon == null) {
            mView.presentError("Please select a Pokemon");
            return;
        }

        mPoGoApi.getEvolutions(mSelectedPokemon.getNumber(), currentCp)
                .subscribe(
                        evolutions -> mView.showEvolutions(evolutions),
                        throwable -> mView.presentError(throwable.getMessage()));
    }

    @Override
    public void loadPokemons() {
        mView.setIndicatorVisible(true);
        mPokeApi.getPokemons(151, null)
                .subscribe(response -> {
                    mPokemons = response.getResults();
                    mView.setIndicatorVisible(false);
                });
    }

    @Override
    public void filterSuggestions(String queryText) {
        if (queryText.isEmpty()) {
            mSelectedPokemon = null;
            mFilteredPokemons = null;
            return;
        }

        Observable.from(mPokemons)
                .filter(pokemon ->
                        pokemon.getName().toLowerCase().contains(queryText.toLowerCase()))
                .limit(MAX_SUGGESTIONS)
                .toList()
                .subscribe(pokemons -> {
                    mFilteredPokemons = pokemons;
                    mView.updateSuggestions(mFilteredPokemons);
                });
    }

    @Override
    public void getSelectedPokemon(int selectedIndex) {
        if (mFilteredPokemons != null && mFilteredPokemons.size() > 0) {
            mSelectedPokemon = mFilteredPokemons.get(selectedIndex);
            mView.showSelectedPokemon(mSelectedPokemon);
        }
    }
}
