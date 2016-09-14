package com.dk.pgt.pokemonSearch;

import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.floater.FloaterContract;

import java.util.List;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public interface PokemonSearchContract {
    interface Presenter extends FloaterContract.Presenter {
        void loadPokemons();

        void filterSuggestions(String queryText);

        void getSelectedPokemon(int selectedIndex);
    }

    interface View extends FloaterContract.View {
        void updateSuggestions(List<Pokemon> pokemons);

        void showSelectedPokemon(Pokemon selectedPokemon);
    }
}
