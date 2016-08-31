package com.dk.pgt.evolve;

import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.data.PokeApi.Pokemon;

import java.util.List;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public interface EvolveContract {
    interface Presenter {
        void calculateEvolutions(int currentCp);

        void loadPokemons();

        void filterSuggestions(String queryText);

        void getSelectedPokemon(int selectedIndex);
    }

    interface View {
        void showEvolutions(List<Evolution> evolutions);

        void updateSuggestions(List<Pokemon> pokemons);

        void showSelectedPokemon(Pokemon selectedPokemon);

        void presentError(String errorMessage);

        void setIndicatorVisible(boolean isVisible);
    }
}
