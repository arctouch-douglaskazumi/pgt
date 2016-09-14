package com.dk.pgt.evolve;

import com.dk.pgt.data.PoGoApi.Evolution;
import com.dk.pgt.pokemonSearch.PokemonSearchContract;

import java.util.List;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public interface EvolveContract {
    interface Presenter extends PokemonSearchContract.Presenter {
        void calculateEvolutions(int currentCp);
    }

    interface View extends PokemonSearchContract.View {
        void showEvolutions(List<Evolution> evolutions);
    }
}
