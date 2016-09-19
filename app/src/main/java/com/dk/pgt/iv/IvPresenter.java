package com.dk.pgt.iv;

import com.dk.pgt.data.PgoivApi.PoGoIvApi;
import com.dk.pgt.data.PokeApi.PokemonApi;
import com.dk.pgt.pokemonSearch.PokemonSearchPresenter;

/**
 * Created by douglaskazumi on 8/21/16.
 */

public class IvPresenter extends PokemonSearchPresenter implements IvContract.Presenter {
    private IvContract.View view;
    private PoGoIvApi poGoIvApi;

    public IvPresenter(IvContract.View view, PoGoIvApi poGoIvApi, PokemonApi pokeApi) {
        super(view, pokeApi);
        this.view = view;
        this.poGoIvApi = poGoIvApi;
    }

    @Override
    public void calculateIv(int currentCp, int currentHp, String requiredDust, int trainerLevel,
                            boolean isPowered) {
        if (selectedPokemon == null) {
            view.presentError("Please select a Pokemon");
            return;
        }

        poGoIvApi
                .getIvData(
                        selectedPokemon.getName(), currentCp, currentHp,
                        requiredDust, trainerLevel, isPowered)
                .subscribe(
                        response -> view.showResult(response),
                        throwable -> view.presentError(throwable.getMessage()));
    }
}
