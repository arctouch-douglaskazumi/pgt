package com.dk.pgt.pokemonSearch;

import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.data.PokeApi.PokemonApi;

import java.util.List;

import rx.Observable;

/**
 * Created by douglaskazumi on 9/6/16.
 */

public class PokemonSearchPresenter implements PokemonSearchContract.Presenter {
    private static final int MAX_SUGGESTIONS = 5;
    private PokemonApi pokeApi;
    private List<Pokemon> pokemons;
    private List<Pokemon> filteredPokemons;
    private PokemonSearchContract.View view;
    protected Pokemon selectedPokemon;

    public PokemonSearchPresenter(PokemonSearchContract.View view, PokemonApi pokeApi) {
        this.view = view;
        this.pokeApi = pokeApi;
    }

    @Override
    public void loadPokemons() {
        view.setIndicatorVisible(true);
        //TODO: Make the number of pokemons a pref
        pokeApi.getPokemons(151, null)
                .subscribe(response -> {
                    pokemons = response.getResults();
                    view.setIndicatorVisible(false);
                });
    }

    @Override
    public void filterSuggestions(String queryText) {
        if (queryText.isEmpty()) {
            selectedPokemon = null;
            filteredPokemons = null;
            return;
        }

        Observable.from(pokemons)
                .filter(pokemon ->
                        pokemon.getName().toLowerCase().contains(queryText.toLowerCase()))
                .limit(MAX_SUGGESTIONS)
                .toList()
                .subscribe(
                        pokemons -> {
                            filteredPokemons = pokemons;
                            view.updateSuggestions(filteredPokemons);
                        },
                        throwable -> view.presentError(throwable.getMessage()));
    }

    @Override
    public void getSelectedPokemon(int selectedIndex) {
        if (filteredPokemons != null && filteredPokemons.size() > 0) {
            selectedPokemon = filteredPokemons.get(selectedIndex);
            view.showSelectedPokemon(selectedPokemon);
        }
    }
}
