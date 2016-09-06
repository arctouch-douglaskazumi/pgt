package com.dk.pgt.evolve;

import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.data.PokeApi.Pokemon;
import com.dk.pgt.floater.FloaterContract;

import java.util.List;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public interface EvolveContract {
    interface Presenter extends FloaterContract.Presenter {
        void calculateEvolutions(int currentCp);

        void loadPokemons();

        void filterSuggestions(String queryText);

        void getSelectedPokemon(int selectedIndex);
    }

    interface View extends FloaterContract.View {
        void showEvolutions(List<Evolution> evolutions);

        void updateSuggestions(List<Pokemon> pokemons);

        void showSelectedPokemon(Pokemon selectedPokemon);
    }
}
