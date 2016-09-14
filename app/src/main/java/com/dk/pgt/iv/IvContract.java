package com.dk.pgt.iv;

import com.dk.pgt.data.PgoivApi.PgoivApiResponse;
import com.dk.pgt.pokemonSearch.PokemonSearchContract;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public interface IvContract {
    interface Presenter extends PokemonSearchContract.Presenter {
        void calculateIv(int currentCp, int currentHp, String requiredDust, int trainerLevel,
                         boolean isPowered);
    }

    interface View extends PokemonSearchContract.View {
        void showResult(PgoivApiResponse response);
    }
}
